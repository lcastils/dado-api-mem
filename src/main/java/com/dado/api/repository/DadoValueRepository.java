package com.dado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dado.api.entity.DadoValueEntity;

public interface DadoValueRepository extends JpaRepository<DadoValueEntity, Long>{

}
