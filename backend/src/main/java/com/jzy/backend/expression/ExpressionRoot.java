package com.jzy.backend.expression;

import com.jzy.backend.DTO.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * 自定义权限校验方法
 * 也可以使用基于配置的方法，这里用自定义类的方法
 * @author JZY
 * @version 1.0
 * Create by 2024/3/24 23:08
 * @Description: TODO
 */
@Component("ExpressionRoot")
public class ExpressionRoot {

    public boolean hasAuthority(String authority) {
        // 获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> permissions = userDetails.getPermissions();
        // 判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}
