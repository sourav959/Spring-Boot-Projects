package com.debugThugs.basicProject.controller;

import com.debugThugs.basicProject.entity.User;
import com.debugThugs.basicProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping()
    ResponseEntity<List<User>> getAllUsers() {
        var response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
