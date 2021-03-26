package com.ruslantech.springbootjs.service;

import com.ruslantech.springbootjs.dao.RoleDao;
import com.ruslantech.springbootjs.dao.UserDao;
import com.ruslantech.springbootjs.model.Role;
import com.ruslantech.springbootjs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private UserDao userDao;
   private RoleDao roleDao;
   private PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
      this.userDao = userDao;
      this.roleDao = roleDao;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void add(User user, String[] rolesName) {
      HashSet<Role> roles = new HashSet<>();

      for(String name: rolesName) {
         roles.add(roleDao.getRoleByName(name));
      }

      user.setRoles(roles);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userDao.add(user);
   }

   @Override
   public Set<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void remove(Long id) {
      userDao.remove(id);
   }

   @Override
   public void update(User user, String[] rolesName) {
      Set<Role> roles = new HashSet<>();
      User oldUser = userDao.getUserById(user.getId());

      if (rolesName != null) {
         for (String name : rolesName) {
            roles.add(roleDao.getRoleByName(name));
         }
      } else {
         roles = oldUser.getRoles();
      }

      if (user.getPassword() != null) {
         oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
      }

      user.setPassword(oldUser.getPassword());
      user.setRoles(roles);
      userDao.update(user);
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   public User getUserByFirstName(String name) {
      return userDao.getUserByFirstName(name);
   }

   @Override
   public boolean checkUserById(Long id) {
      return userDao.checkUserById(id);
   }
}
