package com.springboot.domain;

import java.util.Date;

/**
 * Created by wanglu-jf on 17/8/15.
 * TODO：暂时不设置用户与角色的多对多关系
 */
//@Entity
//@Table(name="s_user_role")
public class SysUserRole {

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column (name="id",length=10)
    private int id;

//    @Column(name="roleId",length=50)
    private String roleId; //角色ID

//    @Column(name="userId",length=50)
    private String userId;//资源ID

//    @Column(name="updateTime")
    private Date updateTime;//更新时间
}
