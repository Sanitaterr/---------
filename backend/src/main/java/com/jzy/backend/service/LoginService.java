package com.jzy.backend.service;

import com.jzy.backend.DO.ResponseResult;
import com.jzy.backend.DO.User;
import org.springframework.stereotype.Service;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 10:35
 * @Description: TODO
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
