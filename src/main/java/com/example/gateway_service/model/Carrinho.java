package com.example.gateway_service.model;


import  java.util.ArrayList;
import java.util.List;
public class Carrinho {
    private int clienteId;
    private  List<Product>  itens = new ArrayList<>();

    public Carrinho(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public void  addProduto(Product item){
        if(item.estaDisponivel()){
            itens.add(item);
        }
    }

    public boolean removerProduto(Product item){
        return  itens.remove(item);
    }

    public double totalizar(){
        double valor_total = itens.stream().mapToDouble(Product::getPrice).sum();
        return  valor_total;
    }

    public List<Product> getItens(){
        return  itens;
    }

    public void cleanCarrinho(){
        itens.clear();
    }

    public int getClienteId(){
        return this.clienteId;
    }
}
