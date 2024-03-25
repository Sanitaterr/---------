package com.jzy.backend.service;

import com.jzy.backend.DO.User;
import com.jzy.backend.DTO.LoginUserDTO;
import com.jzy.backend.VO.LoginVO;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 10:35
 * @Description: TODO
 */
public interface LoginService {
    LoginVO login(LoginUserDTO loginUserDTO);

    void logout();
}
