package com.wine.app.models.repository;

import java.util.Optional;

import com.wine.app.models.Loja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LojaRepository extends JpaRepository<Loja, Long>{
    Optional<Loja> findById(Long id);

    @Query("SELECT COUNT(l) AS total FROM Loja l WHERE (?1 BETWEEN faixa_inicio AND faixa_fim) OR (?2 BETWEEN faixa_inicio AND faixa_fim)")
    int ExistCepInRange(Long faixaInicio, Long faixaFim);
	
}
