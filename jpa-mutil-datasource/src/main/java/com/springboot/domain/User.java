package com.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Entity
public class User {

    /**
     * 城市编号
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 城市名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
