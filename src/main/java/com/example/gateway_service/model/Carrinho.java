package com.example.gateway_service.model;


import  java.util.*;
public class Carrinho {
    private  List<Product>  itens = new ArrayList<>();

    public void  addProduct(Product item){
        itens.add(item);
    }

    public List<Product> getItens(){
        return  itens;
    }

    public void cleanCarrinho(){
        itens.clear();
    }
}
