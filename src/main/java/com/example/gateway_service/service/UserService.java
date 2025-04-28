package com.example.gateway_service.service;

import com.example.gateway_service.model.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    private Map<Integer,User> users = new HashMap<>();
    private int nextId = 1;

    public User createUser(User user){
        user.setId(nextId++);
        users.put(user.getId(), user);
        return user;
    }
    public User getUserById(int id){
        return users.get(id);
    }

    public boolean authenticate(String email, String password){
        boolean output = users.values().stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
        return output; 
    }  
}
