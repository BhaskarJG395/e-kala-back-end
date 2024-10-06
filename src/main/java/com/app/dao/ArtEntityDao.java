package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ArtEntity;

public interface ArtEntityDao extends JpaRepository<ArtEntity, Long>{
//	 Optional<ArtEntity> findByArtNameAndArtType(Str	ing artName, String artType);
}
