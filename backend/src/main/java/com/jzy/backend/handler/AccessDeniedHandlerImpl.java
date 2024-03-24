package com.jzy.backend.handler;

import com.alibaba.fastjson.JSON;
import com.jzy.backend.VO.ResponseResult;
import com.jzy.backend.constance.ResponseResultConstance;
import com.jzy.backend.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 授权异常处理
 * @author JZY
 * @version 1.0
 * Create by 2024/3/23 23:28
 * @Description: TODO
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), ResponseResultConstance.msg.YOU_DO_NOT_HAVE_ENOUGH_PERMISSIONS);
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
