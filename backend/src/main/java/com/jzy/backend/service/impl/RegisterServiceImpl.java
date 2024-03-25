package com.jzy.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzy.backend.DAO.UserDAO;
import com.jzy.backend.DO.User;
import com.jzy.backend.DTO.RegisterUserDTO;
import com.jzy.backend.constance.DTOConstance;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/25 16:23
 * @Description: TODO
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterUserDTO registerUserDTO) {

        String username = registerUserDTO.getUsername();
        String password = registerUserDTO.getPassword();
        String confirmedPassword = registerUserDTO.getConfirmedPassword();
        if (StringUtils.isEmpty(username)) {
            return ResponseResultConstance.msg.USERNAME_CANNOT_BE_EMPTY;
        }
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmedPassword)) {
            return ResponseResultConstance.msg.PASSWORD_CANNOT_BE_EMPTY;
        }
        if (username.length() > DTOConstance.MAXIMUM_USERNAME_LENGTH) {
            return ResponseResultConstance.msg.USERNAME_LENGTH_CANNOT_BE_GREATER_THAN_150;
        }
        if (password.length() > DTOConstance.MAXIMUM_PASSWORD_LENGTH) {
            return ResponseResultConstance.msg.PASSWORD_LENGTH_CANNOT_BE_GREATER_THAN_150;
        }
        if (!password.equals(confirmedPassword)) {
            return ResponseResultConstance.msg.TWO_PASSWORDS_ARE_NOT_EQUAL;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userDAO.selectList(queryWrapper);
        if (!users.isEmpty()) {
            return ResponseResultConstance.msg.USERNAME_ALREADY_EXISTS;
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, encodedPassword);
        userDAO.insert(user);
        return ResponseResultConstance.msg.SUCCESSFUL_REGISTRATION;
    }
}
