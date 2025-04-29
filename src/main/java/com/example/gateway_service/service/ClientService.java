package  com.example.gateway_service.service;

import  java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import  com.example.gateway_service.model.Client;

@Service
public class ClientService{
    private Map<Integer,Client> clientes = new HashMap<>();
    private int nextId = 1;

    public Client createClient(Client cliente) {
        cliente.setId(nextId++);
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public Client getClientById(int id) {
        return clientes.get(id);
    }

    public boolean authenticate(String email, String password) {
        return clientes.values().stream()
                .anyMatch(cliente -> cliente.getEmail().equals(email) && cliente.getPassword().equals(password));
    }
}