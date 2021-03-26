package com.ruslantech.springbootjs.dao;

import com.ruslantech.springbootjs.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
      entityManager.flush();
   }

   @Override
   public Set<User> listUsers() {
      TypedQuery<User> query =  entityManager.createQuery("from User user join fetch user.roles", User.class);
      return new HashSet<>(query.getResultList());
   }

   @Override
   public void remove(Long id) {
      Query query = entityManager.createQuery("delete from User user  where user.id = :id");
      query.setParameter("id", id);
      query.executeUpdate();
   }

   @Override
   public void update(User user) {
      entityManager.merge(user);
   }

   @Override
   public User getUserById(Long id) {
      TypedQuery<User> query =  entityManager.createQuery("from User user join fetch user.roles where user.id=:id", User.class);
      query.setParameter("id", id);
      return query.getSingleResult();
   }

   @Override
   public User getUserByFirstName(String name) {
      TypedQuery<User> query =  entityManager.createQuery("from User user join fetch user.roles where user.firstName=:name", User.class);
      query.setParameter("name", name);
      return query.getSingleResult();
   }

   @Override
   public boolean checkUserById(Long id) {
      TypedQuery<User> query =  entityManager.createQuery("from User user join fetch user.roles where user.id=:id", User.class);
      query.setParameter("id", id);
      return query.getResultList().isEmpty();
   }
}
