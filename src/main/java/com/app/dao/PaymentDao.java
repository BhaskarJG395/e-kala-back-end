package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OrderEntity;
import com.app.entities.PaymentEntity;
import com.app.entities.UserEntity;

public interface PaymentDao extends JpaRepository<PaymentEntity, Long>{
	List<PaymentEntity> findByUser(UserEntity user);
	//boolean existByOrder(Order order);
	
}
