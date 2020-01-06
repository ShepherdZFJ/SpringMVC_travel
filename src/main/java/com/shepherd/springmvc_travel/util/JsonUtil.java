package com.shepherd.springmvc_travel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonUtil {

       private static ObjectMapper mapper = new ObjectMapper();

   //json转Map集合对象
    public static Map<String , Object> jsonToMap(String json){
        Map<String ,Object> map = null;
        try {
            map = mapper.readValue(json, new TypeReference<Map<String,Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    //json转List对象
    public static<T> List<T> josnToList(String json, Class<T>beanType){
        List<T>list = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,beanType);
            list = mapper.readValue(json,javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return list;

    }


}
