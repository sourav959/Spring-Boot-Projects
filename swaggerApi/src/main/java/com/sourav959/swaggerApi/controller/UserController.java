package com.sourav959.swaggerApi.controller;

import com.sourav959.swaggerApi.entity.User;
import com.sourav959.swaggerApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    List<User> getAllUsers(){
        userService.saveUsersFromApi();
        return userService.getAllUsers();
    }

}
