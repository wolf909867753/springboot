package com.springboot.service;

import com.springboot.dao.PermissionDao;
import com.springboot.dao.UserDao;
import com.springboot.domain.Permission;
import com.springboot.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu on 17/8/17.
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    PermissionDao permissionDao;

    /**
     * 重写loadUserByUsername 方法获得 userdetails 类型用户
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userDao.findByUserName(username);
        if(user == null){
            logger.error("[CustomUserService][loadUserByUsername][result[user:{}]:{}]",user);
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }

        List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());//权限集合
        List<GrantedAuthority> grantedAuthorities = new ArrayList <GrantedAuthority>();
        for (Permission permission : permissions) {
            if (permission != null && permission.getName()!=null) {
                GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(SysRole role:user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//            System.out.println(role.getName());
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), authorities);

    }

}
