package com.jzy.backend.filter;

import com.jzy.backend.DTO.UserDetailsImpl;
import com.jzy.backend.constance.RedisConstance;
import com.jzy.backend.util.JwtUtil;
import com.jzy.backend.util.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * jwt认证过滤器
 * @author JZY
 * @version 1.0
 * Create by 2024/3/22 11:58
 * @Description: TODO
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token，解析token获取其中的userid，从redis中获取信息，存入SecurityContextHolder

        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response); // 放行，不用解析了，交给其他过滤器
            return; // 结束递归，防止响应时二次解析
        }

        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        String redisKey = RedisConstance.LOGIN + userid;
        UserDetailsImpl userDetails = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(userDetails)) {
            throw new RuntimeException("用户未登录");
        }

        // TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
