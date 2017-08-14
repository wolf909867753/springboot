package com.springboot.dao.cluster;

import com.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wanglu-jf on 17/8/14.
 */
public interface IUserDao extends JpaRepository<User,Long> {
}
