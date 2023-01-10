package com.sourav959.swaggerApi.service;

import com.sourav959.swaggerApi.entity.User;

import java.util.List;

public interface UserService {

    int saveUsersFromApi();

    List<User> getAllUsers();
}
