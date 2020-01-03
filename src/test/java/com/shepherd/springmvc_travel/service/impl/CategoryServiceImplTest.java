package com.shepherd.springmvc_travel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shepherd.springmvc_travel.domain.Category;
import com.shepherd.springmvc_travel.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void findAll() throws JsonProcessingException {
        List<Category> all = categoryService.findAll();
        System.out.println(all);

    }
}