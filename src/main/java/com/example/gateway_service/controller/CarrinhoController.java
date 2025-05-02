package com.example.gateway_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import  org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.Product;
import com.example.gateway_service.service.CarrinhoService;


@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
        private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Void> adicionarProduto(@PathVariable int clienteId,@RequestBody Product produto) {
        carrinhoService.adicionarProduto(clienteId, produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Product>> visualizarCarrinho(@PathVariable int clienteId) {
        return ResponseEntity.ok(carrinhoService.getCarrinho(clienteId));
    }
}
