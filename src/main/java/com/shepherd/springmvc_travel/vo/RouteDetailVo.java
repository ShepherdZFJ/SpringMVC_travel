package com.shepherd.springmvc_travel.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shepherd.springmvc_travel.domain.Route;
import com.shepherd.springmvc_travel.domain.RouteImg;
import com.shepherd.springmvc_travel.domain.Seller;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class RouteDetailVo implements Serializable {
    private Route route;
    private Seller seller;
    private List<RouteImg> routeImgList;

    public RouteDetailVo(Route route, Seller seller, List<RouteImg> routeImgList) {
        this.route = route;
        this.seller = seller;
        this.routeImgList = routeImgList;
    }
}
