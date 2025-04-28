package com.example.gateway_service.controller;

import com.example.gateway_service.model.User;
import com.example.gateway_service.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Endpoint para buscar um usuário pelo ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Endpoint para login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        boolean authenticated = userService.authenticate(email, senha);
        return authenticated ? "Login bem-sucedido!" : "Credenciais inválidas.";
    }
}
