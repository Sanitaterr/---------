package com.jzy.backend.DTO;

import com.alibaba.fastjson.annotation.JSONField;
import com.jzy.backend.DAO.UserDAO;
import com.jzy.backend.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetails实现类
 * @author JZY
 * @version 1.0
 * Create by 2024/3/21 14:26
 * @Description: TODO
 */
@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;

    /**
     *
     * 存储权限信息
     **/
    private List<String> permissions;

    public UserDetailsImpl(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    /**
     *
     * 存储SpringSecurity所需要的权限信息的集合
     **/
    @JSONField(serialize = false) // SimpleGrantedAuthority是Spring内部提供的类，防止redis存储过程中被序列化而报错
    private List<SimpleGrantedAuthority> authorities;

    /**
     *
     * @return 权限信息
     * @author jzy
     * @create 2024/3/21
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities != null) {
            return authorities;
        }

        // 把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        authorities =  permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
