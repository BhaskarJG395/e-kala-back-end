package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
}
