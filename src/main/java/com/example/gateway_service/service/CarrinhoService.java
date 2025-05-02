package com.example.gateway_service.service;
import java.util.*;

import org.springframework.stereotype.Service;


import com.example.gateway_service.model.Product;


@Service
public class CarrinhoService {

    private Map<Integer, List<Product>> carrinhos = new HashMap<>();

    public void adicionarProduto(int clienteId, Product produto) {
        carrinhos.computeIfAbsent(clienteId, k -> new ArrayList<>()).add(produto);
    }

    public List<Product> getCarrinho(int clienteId) {
        return carrinhos.getOrDefault(clienteId, new ArrayList<>());
    }

    public void limparCarrinho(int clienteId) {
        carrinhos.remove(clienteId);
    }
}