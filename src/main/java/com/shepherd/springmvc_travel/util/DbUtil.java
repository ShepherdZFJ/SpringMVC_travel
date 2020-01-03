package com.shepherd.springmvc_travel.util;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * jpa原生sql查询工具
 *
 */
public class DbUtil {
//
//    /**
//     * jpa原生sql查询后转换为对象
//     * @param sql    查询语句
//     * @param func   转换一条结果为一个对象，定义在每个接收的model内
//     * @param <T>    接收的类型
//     * @return  返回list 或 null
//     */
//    public static  <T> T sqlObj(String sql, Function func){
//        List res = SpringUtil.getBean(EntityManager.class).createNativeQuery(sql).getResultList();
//        if (res == null || res.isEmpty())
//            return null;
//        List<T> resList = new ArrayList<>();
//        ((List<Object[]>) res).forEach(e->resList.add((T)func.apply(e)));
//        return (T) resList;
//
//    }
//
//    /**
//     * 方便对sql只有一条结果,直接返回对象，不然需要sqlObj获取list，然后list.get(0)
//     * @param sql
//     * @param func
//     * @param <T>
//     * @return
//     */
//    public static <T> T sqlObjOne(String sql, Function func){
//        List res = sqlObj(sql,func);
//        if (res == null)
//            return null;
//        if (res.size()>1)
//            throw new RuntimeException("DbUtil.sqlObjOne expect only one result, find "+res.size());
//        return (T)res.get(0);
//    }
//
//    /**
//     * sql查询结果只有一列的，一个总数，一个字符等等
//     * @param sql
//     * @param T
//     * @param <T>
//     * @return
//     */
//    public static <T> T sqlCast(String sql,Class<T> T){
//        Object o = SpringUtil.getBean(EntityManager.class).createNativeQuery(sql).getSingleResult();
//        //对一些类型做特殊处理
//        //count(*)返回BigInteger类型
//        if (o instanceof BigInteger){
//            Long res = ((BigInteger) o).longValue();
//            return (T)res;
//        }
//        return (T)o;
//    }

}
