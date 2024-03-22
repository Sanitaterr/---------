package com.jzy.backend.service.impl;

import com.jzy.backend.DO.ResponseResult;
import com.jzy.backend.DO.User;
import com.jzy.backend.DTO.UserDetailsImpl;
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
     * 使用AuthenticationManager认证;
     * 如果没通过给出对应提示;反之使用userid生成jwt存入ResponseResult返回;
     * 把完整用户信息存入redis，userid作为key;
     * @param user
     * @return ResponseResult
     * @author jzy
     * @create 2024/3/22
     **/
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 理论上登录失败UsernamePasswordAuthenticationToken会自动处理
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String userid = userDetails.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login: " + userid, userDetails);

        return new ResponseResult(200, "登录成功", map);
    }

    /**
     *
     * 获取SecurityContextHolder中的用户id
     * 删除redis中的值
     * @return ResponseResult
     * @author jzy
     * @create 2024/3/22
     **/
    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userid = userDetails.getUser().getId();

        redisCache.deleteObject("login: " + userid);

        return new ResponseResult(200, "注销成功");
    }
}
