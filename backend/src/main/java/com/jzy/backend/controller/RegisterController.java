package com.jzy.backend.controller;

import com.jzy.backend.DTO.RegisterUserDTO;
import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/25 16:08
 * @Description: TODO
 */
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/register/")
    public ResponseResult register(@RequestBody RegisterUserDTO registerUserDTO) {
        return new ResponseResult(HttpStatus.OK.value(), registerService.register(registerUserDTO));
    }
}
