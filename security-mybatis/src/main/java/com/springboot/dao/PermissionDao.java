package com.springboot.dao;

import com.springboot.domain.Permission;

import java.util.List;

/**
 * 权限dao
 * Created by wanglu-jf on 17/8/21.
 */

public interface PermissionDao {

    public List<Permission> findAll();

    public List<Permission> findByAdminUserId(int userId);
}
