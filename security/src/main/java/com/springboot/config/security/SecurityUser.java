package com.springboot.config.security;

import com.springboot.domain.SysRole;
import com.springboot.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by wanglu-jf on 17/8/16.
 */
public class SecurityUser extends SysUser implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUser.class);
    public SecurityUser(SysUser user) {
        logger.info("[SecurityUser][SecurityUser]invoke...start");
        if(user != null){
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setDob(user.getDob());
            this.setSRoles(user.getSysRoles());
        }
        logger.info("[SecurityUser][SecurityUser][result:{}]...end",this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("[SecurityUser][getAuthorities]invoke...start");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<SysRole> userRoles = this.getSysRoles();
        if(userRoles != null){
            for (SysRole role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        logger.info("[SecurityUser][getAuthorities][result:{}]...end",authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
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
