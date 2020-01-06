package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.domain.Route;
import com.shepherd.springmvc_travel.dao.IRouteDao;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;


@Service
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;

    @Override
    public PageBean loadWithPage( Integer currentPage, Integer pageSize, Map map, String order) {
        PageBean<Route> pb = new PageBean<Route>();
        Map<String ,Object> m = map;
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
//                if(cid !=0){
//                    predicates.add(cb.equal(root.get("cid"), cid));
//                }
                for(Map.Entry<String, Object> entry : m.entrySet()){
                    String mapKey = entry.getKey();
                    Object mapValue = entry.getValue();
                    if(mapKey == "rname" && !StringUtils.isEmpty(mapValue)){
                        predicates.add(cb.like(root.get(mapKey).as(String.class), "%"+mapValue+"%"));
                    }
                    if(mapKey == "cid" && !StringUtils.isEmpty(mapValue)){
                        predicates.add(cb.equal(root.get("cid"), mapValue));
                    }
                    if(mapKey == "price_min" && !StringUtils.isEmpty(mapValue)){
                        predicates.add(cb.ge(root.get("price"), Double.parseDouble((String) mapValue)));
                    }
                    if(mapKey == "price_max" && !StringUtils.isEmpty(mapValue)){
                        predicates.add(cb.le(root.get("price"), Double.parseDouble((String) mapValue)));
                    }


                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);
        if(! StringUtils.isEmpty(order)){
            Sort sort = Sort.by(Sort.Direction.DESC,order);
            pageable = PageRequest.of(currentPage-1,pageSize,sort);
        }
        else{
            pageable = PageRequest.of(currentPage-1,pageSize);
        }
        Page<Route> page = routeDao.findAll(spec,pageable);
        pb.setTotalCount(page.getTotalElements());
        pb.setTotalPage(page.getTotalPages());
        pb.setList(page.getContent());
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        return pb;
    }



    @Override
    public PageBean loadWithPage(Integer currentPage, Integer pageSize,String order) {
        PageBean<Route> pageBean = this.loadWithPage(currentPage,pageSize,new HashMap(),order);
        return pageBean;
    }


    @Override
    public PageBean loadWithPage(Integer currentPage, Integer pageSize, List where) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
               // Path id = root.get("id");
                CriteriaBuilder.In<Integer> in = cb.in(root.<Integer>get("id"));
                for(int i=0;i<where.size();i++)
                {
                    in.value((Integer)where.get(i));
                }
                Predicate predicate = cb.and(in);

                return predicate;
            }
        };
        PageBean<Route> pb = new PageBean<Route>();
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);
        Page<Route> page = routeDao.findAll(spec,pageable);
        pb.setTotalCount(page.getTotalElements());
        pb.setTotalPage(page.getTotalPages());
        pb.setList(page.getContent());
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        return pb;
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

    @Override
    public List<Route> findRoute(Collection c) {
        return routeDao.findAllByIdIn(c);
    }
}
