package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ISellerDao extends JpaRepository<Seller,Integer>, JpaSpecificationExecutor<Seller> {
    public Seller findBySid(Integer id);

}
