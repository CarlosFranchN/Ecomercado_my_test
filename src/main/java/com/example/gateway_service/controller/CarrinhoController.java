package com.example.gateway_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.dto.CarrinhoDTO;
import com.example.gateway_service.dto.ItemCarrinhoDTO;
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

    @GetMapping("/teste")
    public ResponseEntity<CarrinhoDTO> teste() {
        ItemCarrinhoDTO item = new ItemCarrinhoDTO(new Product(1,"Teste",2.25, "bla bla bla", 10, (float) 0.5));
        
        List<ItemCarrinhoDTO> itens = List.of(item);
        CarrinhoDTO dto = new CarrinhoDTO(1, "João Silva", itens, 99.9);
        
        return ResponseEntity.ok(dto);
    }

    // 🔹 Visualizar carrinho do cliente (com nome do cliente)
    @GetMapping("/{clienteId}")
    public ResponseEntity<CarrinhoDTO> visualizarCarrinho(@PathVariable int clienteId) {
        System.out.println("Buscando carrinho do cliente: " + clienteId);

        Carrinho carrinho = carrinhoService.getCarrinho(clienteId);
        System.out.println("Carrinho encontrado? " + (carrinho != null));

        if (carrinho == null) {
            String nomeCliente = carrinhoService.getClienteNome(clienteId);
            CarrinhoDTO dto = new CarrinhoDTO(clienteId, nomeCliente, new ArrayList<>(), 0.0);
            System.out.println("Retornando carrinho vazio");
            return ResponseEntity.ok(dto);
        }

        String nomeCliente = carrinhoService.getClienteNome(clienteId);
        List<ItemCarrinhoDTO> itensDto = carrinho.getItens().stream()
                .map(ItemCarrinhoDTO::new)
                .toList();

        double total = carrinho.totalizar();

        CarrinhoDTO dto = new CarrinhoDTO(clienteId, nomeCliente, itensDto, total);
        System.out.println("Retornando carrinho com " + itensDto.size() + " itens");

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{clienteId}/produto/{produtoId}")
    public ResponseEntity<CarrinhoDTO> adicionar(@PathVariable int clienteId,
                                                 @PathVariable int produtoId) {
        Carrinho carrinho = carrinhoService.adicionarProduto(clienteId, produtoId);
        System.err.println(carrinhoService.getCarrinho(clienteId));
        System.err.println("Carlos Carlos Carlos");
        System.out.println(carrinho);
        if (carrinho == null) {
            return ResponseEntity.notFound().build();
        }
    
        String nomeCliente = carrinhoService.getClienteNome(clienteId);
        System.out.println(nomeCliente);
        List<ItemCarrinhoDTO> itensDto = carrinho.getItens().stream()
                .map(ItemCarrinhoDTO::new)
                .toList();
    
        double total = carrinho.totalizar();
    
        CarrinhoDTO dto = new CarrinhoDTO(clienteId, nomeCliente, itensDto, total);
    
        return ResponseEntity.ok(dto);
    }

    // 🔹 Calcular total do carrinho
    @GetMapping("/{clienteId}/total")
    public ResponseEntity<Double> calcularTotal(@PathVariable int clienteId) {
        double total = carrinhoService.totalizar(clienteId);
        return ResponseEntity.ok(total);
    }
}