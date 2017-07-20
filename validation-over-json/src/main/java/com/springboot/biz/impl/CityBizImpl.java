package com.springboot.biz.impl;

import com.springboot.biz.ICityBiz;
import com.springboot.domain.po.City;
import com.springboot.service.ICityService;
import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.customException.ExceptionEnum;
import com.springboot.utils.exceptions.CityErrorInfoEnum;
import com.springboot.utils.exceptions.GlobalErrorInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by wanglu-jf on 17/7/19.
 */
@Service
public class CityBizImpl implements ICityBiz {
    @Autowired
    private ICityService cityService;

    @Override
    public City getCityById(String id) throws GlobalErrorInfoException {
        if(StringUtils.isEmpty(id)){
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        City city = this.cityService.getCityById(id);
        return city;
    }

    public City getById(String id) throws CustomerException{
        if(StringUtils.isEmpty(id)){
            throw new CustomerException(ExceptionEnum.NO_PARAMETER.getCode(),ExceptionEnum.NO_PARAMETER.getMessage());
        }
        City city = this.cityService.getCityById(id);
        return city;
    }
}
