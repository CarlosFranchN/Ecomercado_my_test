package com.example.gateway_service.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
public class Pedido {

    private int id;
    private int idClient;
    private List<Product> itens;
    private LocalDateTime date;
    private double total;

    public Pedido(LocalDateTime date, int id, int idClient, List<Product> itens, double total) {
        this.date = date;
        this.id = id;
        this.idClient = idClient;
        this.itens = itens;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public List<Product> getItens() {
        return itens;
    }

    public void setItens(List<Product> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    private double CalcularTotal(){
        return  this.itens.stream().mapToDouble(Product::getPrice).sum();
    }

    public double calcularImpactoAmbiental(){
        return this.itens.stream().mapToDouble(Product::getEnvironmentalImpact).sum();
    }
    
}
