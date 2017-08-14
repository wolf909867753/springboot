package com.springboot;

import com.springboot.service.ICityService;
import com.springboot.utils.customException.CustomerException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/8/4.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ApplicationTest {
    private static Logger logger = Logger.getLogger(ApplicationTest.class);
    @Autowired
    private ICityService cityService;

    @Test
    public void queryAllCity(){
        try {
            List<Map<String, String>> maps = this.cityService.queryAllCity();
            logger.info(">>>>>>maps size is " + maps.size());
            for (Map<String, String> map :maps){
                logger.info(">>>>>>map[id:" + map.get("id") + ",cityName" + map.get("cityName") + ",description:"+ map.get("description") +"]");
            }
        } catch (CustomerException e) {
            e.printStackTrace();
        }
    }


}
