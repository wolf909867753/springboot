package com.springboot.dao;

import com.springboot.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wanglu-jf on 17/8/16.
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser,Integer> {

//    @Query(" SELECT u FROM SysUser u WHERE u.name = :name ")
    public SysUser findByName(/*@Param("name") */String name);
}
