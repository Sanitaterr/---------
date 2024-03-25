package com.jzy.backend.controller;

import com.jzy.backend.DTO.LoginUserDTO;
import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.DO.User;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 10:32
 * @Description: TODO
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login/")
    public ResponseResult login(@RequestBody LoginUserDTO loginUserDTO){
        return new ResponseResult(HttpStatus.OK.value(), ResponseResultConstance.msg.SUCCESSFUL_LOGIN, loginService.login(loginUserDTO));
    }

    @PostMapping("/user/logout/")
    public ResponseResult logout() {
        loginService.logout();
        return new ResponseResult(HttpStatus.OK.value(), ResponseResultConstance.msg.SUCCESSFUL_CANCELLATION);
    }
}
