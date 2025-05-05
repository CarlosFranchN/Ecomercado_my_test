package com.example.gateway_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gateway_service.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUser_Id(int id);
}
