package com.example.gateway_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.Product;
import com.example.gateway_service.model.User;

@Service
public class CarrinhoService {

    private Map<Integer, Carrinho> carrinhos = new HashMap<>();
    private final UserService userService;
    private final ProductService produtoService;

    public CarrinhoService(UserService userService, ProductService produtoService) {
        this.userService = userService;
        this.produtoService = produtoService;
    }

    // Adiciona produto ao carrinho com validação
    public Carrinho adicionarProduto(int clienteId, int produtoId) {
        // Verifica se cliente existe
        if (userService.getUserById(clienteId) == null) {
            return null;
        }

        // Verifica se produto existe
        Product produto = produtoService.getProductById(produtoId);
        if (produto == null || !produto.estaDisponivel()) {
            return null;
        }

        // Cria ou recupera carrinho
        Carrinho carrinho = carrinhos.computeIfAbsent(clienteId, id -> new Carrinho(id));
        carrinho.addProduto(produto);

        return carrinho;
    }

    // Remove produto do carrinho com validação
    public Carrinho removerProduto(int clienteId, int produtoId) {
        User user = userService.getUserById(clienteId);
        Product produto = produtoService.getProductById(produtoId);

        if (user == null || produto == null) {
            return null;
        }

        Carrinho carrinho = carrinhos.get(clienteId);
        if (carrinho != null) {
            List<Product> itens = carrinho.getItens();
            Product produtoParaRemover = produtoService.getProductById(produtoId);
            itens.remove(produtoParaRemover);
        }

        return carrinho;
    }

    // Retorna carrinho do cliente
    public Carrinho getCarrinho(int clienteId) {
        return carrinhos.get(clienteId);
    }

    // Calcula total do carrinho
    public double totalizar(int clienteId) {
        Carrinho carrinho = carrinhos.get(clienteId);
        return carrinho != null ? carrinho.totalizar() : 0.0;
    }

    // Limpa carrinho
    public boolean limpar(int clienteId) {
        Carrinho carrinho = carrinhos.get(clienteId);
        if (carrinho != null) {
            carrinho.cleanCarrinho();
            return true;
        }
        return false;
    }
}