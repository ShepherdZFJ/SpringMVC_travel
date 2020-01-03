package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {

    public User findByUsername(String name);

    public User findByCode(String code);
    public User findByUsernameAndAndPassword(String username,String password);



}
