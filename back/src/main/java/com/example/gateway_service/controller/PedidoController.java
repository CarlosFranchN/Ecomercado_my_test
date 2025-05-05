package com.example.gateway_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.dto.ItemCarrinhoDTO;
import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.Pedido;
import com.example.gateway_service.repository.CarrinhoRepository;
import com.example.gateway_service.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final CarrinhoRepository carrinhoRepository;

    public PedidoController(PedidoService service,CarrinhoRepository carrinhoRepository) {
        this.pedidoService = service;
        this.carrinhoRepository = carrinhoRepository;
    }

    @GetMapping("/usuario/{clienteId}/carrinho")
    public ResponseEntity<List<ItemCarrinhoDTO>> getItensDoCarrinho(@PathVariable int clienteId) {
        
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId); // ✅ Agora sim!

        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ItemCarrinhoDTO> dtos = carrinho.getItens().stream()
                .map(ItemCarrinhoDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/usuario/{clienteId}")
    public ResponseEntity<Pedido> finalizar(@PathVariable int clienteId) {
        Pedido pedido = pedidoService.criarPedido(clienteId);

        if (pedido == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(pedido);
    }
    // Visualizar histórico de pedidos
    @GetMapping("/usuario/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidos(@PathVariable int clienteId) {
        List<Pedido> pedidos = pedidoService.getPedidosDoUsuario(clienteId);
        return ResponseEntity.ok(pedidos);
    }
}