package com.jzy.backend.service.impl;

import com.jzy.backend.DO.User;
import com.jzy.backend.DTO.UserDetailsImpl;
import com.jzy.backend.VO.LoginVO;
import com.jzy.backend.constance.ExceptionConstance;
import com.jzy.backend.constance.RedisConstance;
import com.jzy.backend.service.LoginService;
import com.jzy.backend.util.JwtUtil;
import com.jzy.backend.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 10:35
 * @Description: TODO
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     *
     * @param user
     * @return ResponseResult
     * @author jzy
     * @create 2024/3/22
     **/
    @Override
    public LoginVO login(User user) {
        // 使用AuthenticationManager认证
        // 如果没通过给出对应提示;反之使用userid生成jwt存入ResponseResult返回
        // 把完整用户信息存入redis，userid作为key

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 理论上登录失败UsernamePasswordAuthenticationToken会自动处理
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException(ExceptionConstance.LOGIN_FAILURE);
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String userid = userDetails.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject(RedisConstance.LOGIN + userid, userDetails);

        return new LoginVO(jwt);
    }

    /**
     *
     * @return ResponseResult
     * @author jzy
     * @create 2024/3/22
     **/
    @Override
    public void logout() {
        // 获取SecurityContextHolder中的用户id
        // 删除redis中的值

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userid = userDetails.getUser().getId();

        redisCache.deleteObject(RedisConstance.LOGIN + userid);
    }
}
