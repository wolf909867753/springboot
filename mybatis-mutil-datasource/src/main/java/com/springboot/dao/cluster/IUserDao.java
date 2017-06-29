package com.springboot.dao.cluster;

import com.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Mapper
public interface IUserDao {

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    public User queryByUserId(@Param("userId") int userId);
}
