package com.ruslantech.springbootjs.controller;

import com.ruslantech.springbootjs.model.User;
import com.ruslantech.springbootjs.service.RoleService;
import com.ruslantech.springbootjs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class RestUserController {

    private UserService userService;

    private RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping("/admin/roles")
    public ResponseEntity getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping(value = "/admin/add")
    public void addUser(@RequestBody User user, @RequestParam(value = "role") String[] rolesName){
        userService.add(user, rolesName);
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.remove(id);
    }

    @PostMapping(value = "/admin/update")
    public void updateUser(@RequestBody User user, @RequestParam(value = "role", required = false) String[] rolesName){
        userService.update(user, rolesName);
    }

}
