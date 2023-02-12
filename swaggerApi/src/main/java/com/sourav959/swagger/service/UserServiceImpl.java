package com.sourav959.swagger.service;

import com.sourav959.swagger.entity.User;
import com.sourav959.swagger.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This is the service implementation of the user service.
 *
 * @author sourav959
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User with id::{} not found", id);
            throw new IllegalArgumentException("User with id::" + id + " not found");
        });
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        getUserById(id);
        userRepository.deleteById(id);
    }
}
