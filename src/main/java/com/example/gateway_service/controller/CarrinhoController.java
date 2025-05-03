package com.example.gateway_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.dto.CarrinhoDTO;
import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService service) {
        this.carrinhoService = service;
    }

@PostMapping("/{clienteId}/produto/{produtoId}")
public ResponseEntity<CarrinhoDTO> adicionar(@PathVariable int clienteId,
                                              @PathVariable int produtoId) {
    Carrinho carrinho = carrinhoService.adicionarProduto(clienteId, produtoId);

    if (carrinho == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(new CarrinhoDTO(carrinho));
}

    @GetMapping("/{clienteId}")
    public ResponseEntity<Carrinho> visualizar(@PathVariable int clienteId) {
        Carrinho carrinho = carrinhoService.getCarrinho(clienteId);
        System.out.println(carrinho.getUser());
        System.out.println(carrinho.getItens());
        return carrinho != null ? ResponseEntity.ok(carrinho) : ResponseEntity.notFound().build();
    }
}