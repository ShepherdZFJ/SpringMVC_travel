package com.shepherd.springmvc_travel.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tab_seller")
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private int sid;//商家id

    @Column(name = "sname")
    private String sname;//商家名称

    @Column(name = "consphone")
    private String consphone;//商家电话

    @Column(name = "address")
    private String address;//商家地址

    /**
     * 无参构造方法
     */
    public Seller(){}

    /**
     * 构造方法
     * @param sid
     * @param sname
     * @param consphone
     * @param address
     */
    public Seller(int sid, String sname, String consphone, String address) {
        this.sid = sid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
    }
}
