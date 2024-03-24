package com.jzy.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jzy.backend.DAO.MenuDAO;
import com.jzy.backend.DAO.UserDAO;
import com.jzy.backend.DO.User;
import com.jzy.backend.DTO.UserDetailsImpl;
import com.jzy.backend.constance.ExceptionConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/21 14:18
 * @Description: TODO
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MenuDAO menuDAO;

    /**
     *
     * 查询用户信息，查询对应权限信息
     * @param username
     * @return UserDetails
     * @author jzy
     * @create 2024/3/21
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userDAO.selectOne(queryWrapper);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(ExceptionConstance.USERNAME_DOES_NOT_EXIST);
        }

        //TODO 查询对应权限信息
        List<String> list = menuDAO.selectPermsByUserId(user.getId());

        // 将数据封装为UserDetails返回
        return new UserDetailsImpl(user, list);
    }
}
