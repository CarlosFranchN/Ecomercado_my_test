package com.example.gateway_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gateway_service.model.User;


@Service
public class UserService {
    
    private Map<Integer,User> users = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private int nextId = 1;

    public User createUser(User user){
        user.setId(nextId++);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.put(user.getId(), user);
        return user;
    }
        
    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }
    
    public User getUserById(int id){
        return users.get(id);
    }
    
    public boolean authenticate(String email, String senha) {
        return users.values().stream()
            .anyMatch(u -> u.getEmail().equals(email) && passwordEncoder.matches(senha, u.getPassword()));
    }

}


