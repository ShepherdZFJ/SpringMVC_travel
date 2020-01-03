package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.dao.ISellerDao;
import com.shepherd.springmvc_travel.domain.Seller;
import com.shepherd.springmvc_travel.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private ISellerDao sellerDao;
    @Override
    public Seller findSeller(Integer id) {
        return sellerDao.findBySid(id);
    }
}
