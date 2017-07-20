package com.springboot.controller;

import com.springboot.biz.ICityBiz;
import com.springboot.domain.po.City;
import com.springboot.domain.vo.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu-jf on 17/7/19.
 */
//@Controller
@RestController
@RequestMapping("/city")
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private ICityBiz cityBiz;

    @RequestMapping("/get")
    public ResultMsg getCityById(@RequestParam("id") String id){
        City city = null;
        try {
            city = this.cityBiz.getCityById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        logger.info("response message[city:{}]",city);
        List lists = new ArrayList();
        lists.add(city);
        ResultMsg result = new ResultMsg(lists);
        logger.info("response message[result:{}]",result);
        return result;
    }

    @RequestMapping("/query/{id}")
    public String queryById(@PathVariable("id") String id, Model model){
        City city = null;
        try {
            city = this.cityBiz.getCityById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("response message[city:{}]",city);
        model.addAttribute("city",city);
        return "City";
//        return "city";
    }

    @RequestMapping("/query")
    public ResultMsg geById(@RequestParam("id") String id){
        City city = null;
        try {
            city = this.cityBiz.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        logger.info("response message[city:{}]",city);
        List lists = new ArrayList();
        lists.add(city);
        ResultMsg result = new ResultMsg(lists);
        logger.info("response message[result:{}]",result);
        return result;
    }
}
