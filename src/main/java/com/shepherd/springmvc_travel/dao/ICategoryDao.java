package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ICategoryDao extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {

    public List<Category> findAll();

}
