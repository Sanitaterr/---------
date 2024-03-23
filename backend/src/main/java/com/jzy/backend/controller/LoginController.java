package com.jzy.backend.controller;

import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.DO.User;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult login(@RequestBody User user){
        return new ResponseResult(ResponseResultConstance.code.SUCCESS, ResponseResultConstance.msg.SUCCESSFUL_LOGIN, loginService.login(user));
    }

    @PostMapping("/user/logout/")
    public ResponseResult logout() {
        loginService.logout();
        return new ResponseResult(ResponseResultConstance.code.SUCCESS, ResponseResultConstance.msg.SUCCESSFUL_CANCELLATION);
    }
}
