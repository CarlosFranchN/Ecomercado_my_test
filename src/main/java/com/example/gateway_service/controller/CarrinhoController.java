package com.example.gateway_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.PathVariable;
import  org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // 🔹 Visualizar carrinho de um cliente
    @GetMapping("/{clienteId}")
    public ResponseEntity<Carrinho> visualizarCarrinho(@PathVariable int clienteId) {
        Carrinho carrinho = carrinhoService.getCarrinho(clienteId);

        if (carrinho == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrinho);
    }

    // 🔹 Adicionar produto ao carrinho e retornar carrinho atualizado
    @PostMapping("/{clienteId}/produto")
    public ResponseEntity<Carrinho> adicionarProduto(@PathVariable int clienteId,
                                                     @RequestBody Product produto) {
        Carrinho carrinhoAtualizado = carrinhoService.adicionarProduto(clienteId, produto);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    // 🔹 Remover produto do carrinho e retornar carrinho atualizado
    @DeleteMapping("/{clienteId}/produto")
    public ResponseEntity<Carrinho> removerProduto(@PathVariable int clienteId,
                                                   @RequestBody Product produto) {
        Carrinho carrinhoAtualizado = carrinhoService.removerProduto(clienteId, produto);

        if (carrinhoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrinhoAtualizado);
    }

    // 🔹 Calcular total do carrinho
    @GetMapping("/{clienteId}/total")
    public ResponseEntity<Double> calcularTotal(@PathVariable int clienteId) {
        double total = carrinhoService.totalizar(clienteId);
        return ResponseEntity.ok(total);
    }

}
