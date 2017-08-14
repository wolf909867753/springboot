package com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisObjectSerializerTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void redisObjectSerializerTest(){
//        City city = new City();
//        city.setId(225L);
//        city.setDescription("我爱北京天安门");
//        city.setProvinceId(3L);
//        city.setCityName("潍坊市");
//        Map<String,Object> map = convert2Map(city);
//        redisTemplate.opsForHash().putAll("user_"+map.get("id"),map);
    }

    private static Map<String,Object> convert2Map(Object obj){
        Map<String,Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            String name = field.getName();
            System.out.println("[field:]"+ name);
            try {
                Method method = clazz.getMethod("get" + getMethodName(name));
                Object value = method.invoke(obj);
                System.out.println("[value]="+value);
                map.put(name,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                if (field.getGenericType().toString().equals("class java.lang.Short")) {
//                    Method m = (Method) obj.getClass().getMethod("get" + getMethodName(name));
//                    Short val = (Short) m.invoke(obj);
//                    if (val != null) {
//                        System.out.println("Short type:" + val);
//                    }
//                }
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        return map;
    }

    private static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}