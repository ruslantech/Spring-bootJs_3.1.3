package com.ruslantech.springbootjs.dao;

import com.ruslantech.springbootjs.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    Role getRoleByName(String name);

}
