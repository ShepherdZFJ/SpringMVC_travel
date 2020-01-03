package com.shepherd.springmvc_travel.controller;

import com.shepherd.springmvc_travel.domain.*;
import com.shepherd.springmvc_travel.service.IFavoriteService;
import com.shepherd.springmvc_travel.service.IRouteImgService;
import com.shepherd.springmvc_travel.service.IRouteService;
import com.shepherd.springmvc_travel.service.ISellerService;
import com.shepherd.springmvc_travel.vo.RouteDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private IRouteService routeService;
    @Autowired
    private IRouteImgService routeImgService;
    @Autowired
    private ISellerService sellerService;

    @Autowired
    private IFavoriteService favoriteService;

    @RequestMapping("/getRouteList")
    public ModelAndView getRouteList(){
        ModelAndView mv = new ModelAndView("route_list");
        return mv;
    }

    @RequestMapping("/getRouteInfo")
    @ResponseBody
    public PageBean getRouteInfo(@RequestParam(name = "cid")String cidData, @RequestParam(name = "currentPage") String currentPageData,
                                 @RequestParam(name = "rname")String rname){
        Integer cid = 0;//类别id
        //2.处理参数
        if(cidData != null && cidData.length() > 0 && !"null".equals(cidData)){
            cid = Integer.parseInt(cidData);
        }
        Integer currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageData != null && currentPageData.length() > 0 && !"null".equals(currentPageData)){
            currentPage = Integer.parseInt(currentPageData);
        }else{
            currentPage = 1;
        }
        Integer pageSize = 5;
        PageBean pageBean = routeService.loadWithPage(cid, currentPage, pageSize,rname,"");
        System.out.println(pageBean);
        return pageBean;
    }

    @RequestMapping("/getRouteDetail")
    public ModelAndView getRouteDetail(){
          ModelAndView mv = new ModelAndView("route_detail");
            return mv;
    }


    @RequestMapping("/getRouteDetailInfo")
    @ResponseBody
    public RouteDetailVo getRouteDetailInfo(@RequestParam(name = "rid") String ridData){
        Route route1 = new Route();
        //List<RouteImg> routeImg1 = new ArrayList<>();
        //Seller seller1 = new Seller();

        Integer rid = 0;//类别id
        //2.处理参数
        if(ridData != null && ridData.length() > 0 && !"null".equals(ridData)){
            rid = Integer.parseInt(ridData);
        }
        //根据rid查询得到线路信息数据
         Route route = routeService.findRoute(rid);
        /**
         * 注意，由于我根据id 查找路线使用jpa原生的findOne方法，所以生成的是底层实现的动态代理对象Route
         * 这样在传递给vo类时不能转换成json字符串，所以需要下面的操作
         * 查找图片和对应的卖家的信息都是我在dao层封装相应的查找方法，其返回类型就是对应的实体类，所以生成的
         * 不是动态代理类，不需要转换操作
         */
        BeanUtils.copyProperties(route,route1);
        //得到项目的图片信息
        List<RouteImg> routeImgs = routeImgService.findRouteImg(route.getId());
        //BeanUtils.copyProperties(routeImgs,routeImg1);
        //得到卖家信息
        Seller seller = sellerService.findSeller(route.getSid());
       // BeanUtils.copyProperties(seller,seller1);
        RouteDetailVo routeDetailVo = new RouteDetailVo(route1,seller,routeImgs);
        System.out.println(routeDetailVo);

        return routeDetailVo;
    }

    @RequestMapping("/isFavorite")
    @ResponseBody
    public Boolean isFavorite(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "rid") String ridData){
        Integer rid = 0;//类别id
        if(ridData != null && ridData.length() > 0 && !"null".equals(ridData)){
            rid = Integer.parseInt(ridData);
        }

        //2. 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getId();
        }
        Boolean flag = favoriteService.isFavorite(rid, uid);
        return flag;
    }

    @RequestMapping("/addFavorite")
    public void addFavorite(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "rid") String ridData){
        Integer rid = 0;//类别id
        if(ridData != null && ridData.length() > 0 && !"null".equals(ridData)){
            rid = Integer.parseInt(ridData);
        }
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getId();
        }

        //3. 调用service添加
        favoriteService.addFavorite(rid,uid);

    }

}
