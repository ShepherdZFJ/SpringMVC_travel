package com.shepherd.springmvc_travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shepherd.springmvc_travel.domain.Category;
import com.shepherd.springmvc_travel.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Category> findAllCategory() throws JsonProcessingException {

        List<Category> categories = categoryService.findAll();
        return categories;




    }
}
