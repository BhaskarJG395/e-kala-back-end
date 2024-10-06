package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OrderEntity;

public interface OrderDao extends JpaRepository<OrderEntity, Long> {

}
