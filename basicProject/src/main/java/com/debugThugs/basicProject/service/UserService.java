package com.debugThugs.basicProject.service;

import com.debugThugs.basicProject.entity.User;
import com.debugThugs.basicProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }
}
