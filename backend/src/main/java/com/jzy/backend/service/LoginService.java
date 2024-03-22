package com.jzy.backend.service;

import com.jzy.backend.DO.ResponseResult;
import com.jzy.backend.DO.User;
import com.jzy.backend.VO.LoginVO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 10:35
 * @Description: TODO
 */
public interface LoginService {
    LoginVO login(User user);

    void logout();
}
