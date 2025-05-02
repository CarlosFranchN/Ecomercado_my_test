package com.example.gateway_service.dto;

import com.example.gateway_service.model.Product;

public class ItemCarrinhoDTO {
    private int produtoId;
    private String name;
    private double price;

    
    public ItemCarrinhoDTO(Product produto) {
        this.produtoId = produto.getId();
        this.name = produto.getName();
        this.price = produto.getPrice();
    }
    
    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getName() {
        return name;
    }

    public void setNome(String new_name) {
        this.name = new_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double new_price) {
        this.price = new_price;
    }


    // getters
}