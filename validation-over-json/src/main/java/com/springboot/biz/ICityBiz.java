package com.springboot.biz;

import com.springboot.domain.po.City;
import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.exceptions.GlobalErrorInfoException;

/**
 * Created by wanglu-jf on 17/7/19.
 */
public interface ICityBiz {

    public City getCityById(String id)  throws GlobalErrorInfoException;

    public City getById(String id) throws CustomerException;
}
