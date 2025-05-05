package com.example.gateway_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.gateway_service.model.Pedido;

public class PedidoDTO {
    private int id;
    private LocalDateTime data;
    private String status;
    private double total;
    private double impactoAmbiental;
    private List<ItemCarrinhoDTO> itens;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.status = pedido.getStatus();
        this.total = pedido.calcularTotal();
        this.impactoAmbiental = pedido.calcularImpactoAmbiental();
        this.itens = pedido.getItens().stream()
                .map(ItemCarrinhoDTO::new)
                .toList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getImpactoAmbiental() {
        return impactoAmbiental;
    }

    public void setImpactoAmbiental(double impactoAmbiental) {
        this.impactoAmbiental = impactoAmbiental;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    // getters
}