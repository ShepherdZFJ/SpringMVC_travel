package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.domain.Route;
import com.shepherd.springmvc_travel.dao.IRouteDao;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;


@Service
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;
    @Override
    public PageBean<Route> loadWithPage(Integer cid, Integer currentPage, Integer pageSize,String rname,String order) {

        PageBean<Route> pb = new PageBean<Route>();
        //判断是否需要排序
        Boolean flag = false;
        if(order != null && order.length() > 0 && !"null".equals(order)){
            flag = true;
        }
        //使用匿名内部类的方式，创建一个Specification的实现类，并实现toPredicate方法
        Specification<Route> spec = new Specification<Route>() {
            public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Boolean flag1 = false;
                Boolean flag2 = false;

                if(cid != 0){
                    flag1 = true;

                }
                if(rname != null && rname.length() > 0 && !"null".equals(rname)){
                    flag2 = true;
                }
                if(flag1 && flag2){
                    //1.获取比较属性
                    //2.构造比较条件
                    Path<Object> c = root.get("cid");
                    Predicate predicate1 = cb.equal(c, cid);
                    Path<Object> r = root.get("rname");
                    Predicate predicate2 = cb.like(r.as(String.class), "%" + rname + "%");
                    Predicate predicate = cb.and(predicate1, predicate2);
                    return predicate;

                }
                else if(flag1 && !flag2){
                    Path<Object> c = root.get("cid");
                    Predicate predicate1 = cb.equal(c, cid);
                   return predicate1;
                }else if(!flag1 && flag2){
                    Path<Object> r = root.get("rname");
                    Predicate predicate2 = cb.like(r.as(String.class), "%" + rname + "%");
                    return predicate2;
                }else{
                    Predicate predicate = cb.and();
                    return predicate;
                }
            }
        };
        //        System.out.println(page.getContent()); //得到数据集合列表
        //        System.out.println(page.getTotalElements());//得到总条数
        //        System.out.println(page.getTotalPages());//得到总页数

        if(flag){
            Sort sort = Sort.by(Sort.Direction.DESC,order);
            Pageable pageable = PageRequest.of(currentPage-1,pageSize,sort);
            Page<Route> page = routeDao.findAll(spec,pageable);
            pb.setTotalCount(page.getTotalElements());
            pb.setTotalPage(page.getTotalPages());
            pb.setList(page.getContent());
        }
        else{
            Pageable pageable = PageRequest.of(currentPage-1,pageSize);
            Page<Route> page = routeDao.findAll(spec,pageable);
            pb.setTotalCount(page.getTotalElements());
            pb.setTotalPage(page.getTotalPages());
            pb.setList(page.getContent());
        }

        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        return pb;
    }

    @Override
    public PageBean loadWithPage(Integer currentPage, Integer pageSize, String rname, String order) {
        PageBean<Route> pageBean = this.loadWithPage(0, currentPage, pageSize, rname, order);
        return pageBean;
    }

    @Override
    public PageBean loadWithPage(Integer currentPage, Integer pageSize,String order) {
        PageBean<Route> pageBean = this.loadWithPage(0, currentPage, pageSize, "", order);
        return pageBean;
    }

    @Override
    public Route findRoute(Integer id) {
        return routeDao.getOne(id);
    }

    @Override
    public Boolean updateRouteCount(Route route) {
        routeDao.save(route);
        return true;
    }
}
