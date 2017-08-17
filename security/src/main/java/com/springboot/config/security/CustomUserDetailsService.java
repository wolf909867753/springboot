package com.springboot.config.security;

import com.springboot.domain.SysUser;
import com.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/8/16.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private UserService userService;//业务服务类

    public CustomUserDetailsService() {
        logger.info("[CustomUserDetailsService][CustomUserDetailsService][result:{}]invoke...",this);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        logger.info("[CustomUserDetailsService][loadUserByUsername]invoke...start");
        //SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SysUser中的name作为用户名:
        SysUser user = userService.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("UserName : " + name + " not found");
        }
        // SecurityUser实现UserDetails并将SysUser的name映射为username
        SecurityUser securityUser = new SecurityUser(user);
        logger.info("[CustomUserDetailsService][loadUserByUsername][result:{}]...end",securityUser);
        return  securityUser;
    }
}
