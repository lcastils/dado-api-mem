package com.dado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dado.api.entity.DadoEntity;

public interface DadoRepository extends JpaRepository<DadoEntity, Long>{

}
