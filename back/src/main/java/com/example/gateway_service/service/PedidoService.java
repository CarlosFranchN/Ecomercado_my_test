package com.example.gateway_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.ItemCarrinho;
import com.example.gateway_service.model.Pedido;
import com.example.gateway_service.repository.CarrinhoRepository;
import com.example.gateway_service.repository.ItemCarrinhoRepository;
import com.example.gateway_service.repository.PedidoRepository;

@Service
public class PedidoService {

    private final CarrinhoRepository carrinhoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;

    public PedidoService(CarrinhoRepository carrinhoRepo,
                         PedidoRepository pedidoRepo,
                         ItemCarrinhoRepository itemRepo) {
        this.carrinhoRepository = carrinhoRepo;
        this.pedidoRepository = pedidoRepo;
        this.itemCarrinhoRepository = itemRepo;
    }

    public Pedido criarPedido(int clienteId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setUser(carrinho.getUser());

        List<ItemCarrinho> itensMergeados = new ArrayList<>();

        for (ItemCarrinho item : carrinho.getItens()) {
            ItemCarrinho salvo = itemCarrinhoRepository.save(item); // ✅ Salva o item primeiro
            itensMergeados.add(salvo);
        }

        pedido.setItens(itensMergeados);
        pedido.setStatus("pendente");
        pedido = pedidoRepository.save(pedido); // ✅ Agora funciona

        // Limpa o carrinho
        carrinho.getItens().clear();
        carrinhoRepository.save(carrinho);

        return pedido;
    }

    public List<Pedido> getPedidosDoUsuario(int clienteId) {
        return pedidoRepository.findByUser_Id(clienteId);
    }
}