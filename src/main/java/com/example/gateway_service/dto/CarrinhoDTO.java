package com.example.gateway_service.dto;

import java.util.List;

public class CarrinhoDTO {
    private int clienteId;
    private String clienteNome;
    private List<ItemCarrinhoDTO> itens;
    private double total;

    public CarrinhoDTO(int clienteId, String clienteNome, List<ItemCarrinhoDTO> itens, double total) {
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.itens = itens;
        this.total = total;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}