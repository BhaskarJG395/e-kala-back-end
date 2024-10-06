package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DeliveryEntity;
import com.app.entities.UserEntity;

public interface DeliveryDao extends JpaRepository<DeliveryEntity, Long>{
	DeliveryEntity findByPaymentUser(UserEntity user);
}
