package com.shepherd.springmvc_travel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shepherd.springmvc_travel.domain.Category;
import com.shepherd.springmvc_travel.dao.ICategoryDao;
import com.shepherd.springmvc_travel.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

   @Autowired
    private RedisTemplate<String, String> redisTemplate;

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public  List<Category> findAll() throws JsonProcessingException {



        List<Category> cs = null;

        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3查询sortedset中的分数(cid)和值(cname)
       // Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        String category_str= redisTemplate.boundValueOps("category").get();


        //2.判断查询的集合是否为空
        if (category_str == null || category_str.length() == 0) {

            System.out.println("从数据库查询....");
            //3.如果为空,需要从数据库查询,在将数据存入redis
            //3.1 从数据库查询
            cs = categoryDao.findAll();
            //转换成json格式字符串
            String str = mapper.writeValueAsString(cs);


            //3.2 将集合数据存储到redis中的 category的key
            redisTemplate.boundValueOps("category").set(str);
            System.out.println("===============从数据库获得数据===============");
        } else {
            System.out.println("从redis中查询.....");
            JavaType javaType = getCollectionType(ArrayList.class, Category.class);
             cs =  (List<Category>)mapper.readValue(category_str, javaType);

        }
        return cs;
    }

    public  JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


}
