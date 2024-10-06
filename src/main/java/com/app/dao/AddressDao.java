package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.AddressEntity;
import com.app.entities.UserEntity;

public interface AddressDao extends JpaRepository<AddressEntity, Long> {
	
	AddressEntity findByUser(UserEntity user);

}
