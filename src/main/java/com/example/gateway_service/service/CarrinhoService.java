package com.example.gateway_service.service;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.ItemCarrinho;
import com.example.gateway_service.model.Product;
import com.example.gateway_service.repository.CarrinhoRepository;
import com.example.gateway_service.repository.ProductRepository;
import com.example.gateway_service.repository.UserRepository;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CarrinhoService(CarrinhoRepository repository,
                           ProductRepository productRepo,
                           UserRepository userRepo) {
        this.carrinhoRepository = repository;
        this.productRepository = productRepo;
        this.userRepository = userRepo;
    }

    public Carrinho adicionarProduto(int clienteId, int produtoId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setClienteId(clienteId); 
            carrinho = carrinhoRepository.save(carrinho); // ✅ Salva o carrinho primeiro!
        }
        
        Product produto = productRepository.findById(produtoId).orElse(null);
        System.out.println("Carlos Carlos Carlos Carlos Carlos");
        System.out.println(produto.getId());
        System.out.println(produto.getName());
        System.out.println(produto.estaDisponivel());

        System.out.println();
    
        if (produto != null && produto.estaDisponivel()) {
            ItemCarrinho item = new ItemCarrinho(produto, 1);
            System.out.println("ITEM!");
            System.err.println(item.getProduto());
            carrinho.addProduto(item);
            carrinho = carrinhoRepository.save(carrinho); // ✅ Agora sim, salva os itens
        }
    
        return carrinho;
    }
    public Carrinho getCarrinho(int clienteId) {
        return carrinhoRepository.findByClienteId(clienteId);
    }
}