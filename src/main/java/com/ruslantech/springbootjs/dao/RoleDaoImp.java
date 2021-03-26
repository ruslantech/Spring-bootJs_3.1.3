package com.ruslantech.springbootjs.dao;

import com.ruslantech.springbootjs.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        TypedQuery<Role> query =  entityManager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query =  entityManager.createQuery("from Role role where role.nameRole=:name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
