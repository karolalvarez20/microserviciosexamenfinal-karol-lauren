package com.portatil.service.reposirorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portatil.service.entidades.Portatil;


@Repository
public interface PortatilRepository extends  JpaRepository<Portatil, Integer>{
	List<Portatil> findByUsuarioId(int usuarioId);

}
