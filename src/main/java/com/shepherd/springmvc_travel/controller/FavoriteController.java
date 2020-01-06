package com.shepherd.springmvc_travel.controller;

import com.shepherd.springmvc_travel.domain.Favorite;
import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.domain.User;
import com.shepherd.springmvc_travel.service.IFavoriteService;
import com.shepherd.springmvc_travel.service.IRouteService;
import com.shepherd.springmvc_travel.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private IRouteService routeService;
    @Autowired
    private IFavoriteService favoriteService;

    @RequestMapping("/getFavoriteRankList")
    public ModelAndView getFavoriteRankList(){
        ModelAndView mv = new ModelAndView("favoriterank");
        return mv;
    }

    @RequestMapping("/getMyFavorite")
    public ModelAndView getMyFavorite(){
        ModelAndView mv = new ModelAndView("myfavorite");
        return mv;
    }

    @RequestMapping("/getFavoriteRankInfo")
    @ResponseBody
    public PageBean getFavoriteRankInfo(@RequestParam(name = "param")String param){
//        if(currentPageData != null && currentPageData.length() > 0 && !"null".equals(currentPageData)){
//            currentPage = Integer.parseInt(currentPageData);
//        }else{
//            currentPage = 1;
//        }
        Map<String, Object> map = JsonUtil.jsonToMap(param);
        Integer currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(map.containsKey("currentPage")){
            if(map.get("currentPage")!= null){
                currentPage = (Integer)map.get("currentPage");
            }
            map.remove("currentPage");
        }
        Integer pageSize = 8;
        PageBean pageBean = routeService.loadWithPage( currentPage, pageSize,map,"count");
        System.out.println(pageBean);
        return pageBean;
    }

    @RequestMapping("/getMyFavoriteInfo")
    @ResponseBody
    public PageBean getMyFavoriteInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(name ="currentPage")String currentPageData){
        PageBean pageBean = null;
        Integer currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageData != null && currentPageData.length() > 0 && !"null".equals(currentPageData)){
            currentPage = Integer.parseInt(currentPageData);
        }else{
            currentPage = 1;
        }
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            return pageBean;
        }else{
            //用户已经登录
            uid = user.getId();
        }
        Integer pageSize = 8;
        List<Favorite> favorites = favoriteService.findAll(uid);
        if(favorites != null && favorites.size() > 0){
            List<Integer> list = new ArrayList<>();
            for(Favorite favorite:favorites)
            {
                list.add(favorite.getRid());
            }
            pageBean = routeService.loadWithPage(currentPage,pageSize,list);
        }
        System.out.println(pageBean);
        return pageBean;



    }
}


