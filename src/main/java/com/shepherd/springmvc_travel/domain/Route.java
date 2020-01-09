package com.shepherd.springmvc_travel.domain;

import com.shepherd.springmvc_travel.domain.commom.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "tab_route")
public class Route extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private Integer id;

    @Column(name = "rname")
    private String rname;

    @Column(name = "price")
    private Double price;

    @Column(name = "routeIntroduce")
    private String routeIntroduce;

    @Column(name = "rflag")
    private String rflag;

    @Column(name = "rdate")
    private String rdate;

    @Column(name = "isThemeTour")
    private String isThemeTour;

    @Column(name = "count")
    private Integer count;

    @Column(name = "cid")
    private Integer cid;

    @Column(name = "rimage")
    private String rimage;

    @Column(name = "sid")
    private Integer sid;

    @Column(name = "sourceId")
    private String sourceId;
}
