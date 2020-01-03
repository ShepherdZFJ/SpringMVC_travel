package com.shepherd.springmvc_travel.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    private Long totalCount;//总记录数
    private Integer totalPage;//总页数
    private Integer currentPage;//当前页码
    private Integer pageSize;//每页显示的条数

    private List<T> list;//每页显示的数据集合


}
