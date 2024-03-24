package com.jzy.backend.handler;

import com.alibaba.fastjson.JSON;
import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 认证异常处理
 * @author JZY
 * @version 1.0
 * Create by 2024/3/23 23:17
 * @Description: TODO
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), ResponseResultConstance.msg.USER_AUTHENTICATION_FAILED_PLEASE_LOG_IN_AGAIN);
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
