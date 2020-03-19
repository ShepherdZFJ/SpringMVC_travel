package com.shepherd.springmvc_travel.domain;

import com.shepherd.springmvc_travel.domain.commom.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table( name ="tab_cart")
public class Cart extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "route_id")
    private Integer routeId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "checked")
    private Integer checked;

}