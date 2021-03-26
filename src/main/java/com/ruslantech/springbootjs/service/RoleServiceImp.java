package com.ruslantech.springbootjs.service;

import com.ruslantech.springbootjs.dao.RoleDao;
import com.ruslantech.springbootjs.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements com.ruslantech.springbootjs.service.RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
