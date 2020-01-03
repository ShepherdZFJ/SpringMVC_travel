package com.shepherd.springmvc_travel.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tab_route_img")
public class RouteImg implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rgid")
    private int rgid;//商品图片id

    @Column(name = "rid")
    private int rid;//旅游商品id

    @Column(name = "bigPic")
    private String bigPic;//详情商品大图

    @Column(name = "smallPic")
    private String smallPic;//详情商品小图

    /**
     * 无参构造方法
     */
    public RouteImg() {
    }

    /**
     * 有参构造方法
     * @param rgid
     * @param rid
     * @param bigPic
     * @param smallPic
     */
    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }
}
