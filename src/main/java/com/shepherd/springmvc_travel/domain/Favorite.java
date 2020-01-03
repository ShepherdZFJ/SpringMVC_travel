package com.shepherd.springmvc_travel.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tab_favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private Integer fid;

    @Column(name = "rid")
    private Integer rid;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;


    @Column(name = "uid")
    private Integer uid;
}
