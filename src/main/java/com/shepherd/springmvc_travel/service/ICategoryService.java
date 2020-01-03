package com.shepherd.springmvc_travel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shepherd.springmvc_travel.domain.Category;

import java.util.List;

public interface ICategoryService {
    public  List<Category> findAll() throws JsonProcessingException;
}
