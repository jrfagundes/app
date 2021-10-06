package com.wine.app.models.repository;

import java.util.Optional;

import com.wine.app.models.Loja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long>{
    Optional<Loja> findById(Long id);
	
}
