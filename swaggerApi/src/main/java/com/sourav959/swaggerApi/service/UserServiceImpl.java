package com.sourav959.swaggerApi.service;

import com.sourav959.swaggerApi.entity.User;
import com.sourav959.swaggerApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    String usersUrl="https://gorest.co.in/public/v2/users";


    @Override
    public int saveUsersFromApi() {
        ResponseEntity responseEntity=restTemplate.getForEntity(usersUrl, User[].class);
        if(!responseEntity.getStatusCode().is2xxSuccessful())
            throw new RuntimeException("Error From Response");
        User[] users= (User[]) responseEntity.getBody();
        List<User> userList=null;
        if(users!=null && users.length>0)
            userList=(List<User>)userRepository.saveAll(Arrays.asList(users));
        return userList==null?0:userList.size();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
