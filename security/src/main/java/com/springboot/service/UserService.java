package com.springboot.service;

import com.springboot.dao.SysUserDao;
import com.springboot.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanglu-jf on 17/8/15.
 */
@Service
public class UserService {

//    @Autowired
//    private SResourceVODao sResourceVODao;
//
//    @Autowired
//    private SRoleVODao sRoleVODao;

    @Autowired
    private SysUserDao sysUserDao;

    public SysUser findByName(String userName) {
        SysUser sysUser = this.sysUserDao.findByName(userName);
        return sysUser;
    }

    @Transactional
    public SysUser update(SysUser sysUser){
        SysUser newSysUser = this.sysUserDao.saveAndFlush(sysUser);
        return newSysUser;
    }
}
