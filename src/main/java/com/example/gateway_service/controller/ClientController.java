package com.example.gateway_service.controller;

import  org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.model.Client;
import com.example.gateway_service.service.ClientService;


@RestController
@RequestMapping("/clientes")
public class ClientController {
    private final ClientService clientService ;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return  clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable int id){
        return clientService.getClientById(id);
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        boolean authenticated = clientService.authenticate(email, senha);
        return authenticated ? "Login de cliente bem-sucedido!" : "Credenciais inválidas para cliente.";
    }

}
