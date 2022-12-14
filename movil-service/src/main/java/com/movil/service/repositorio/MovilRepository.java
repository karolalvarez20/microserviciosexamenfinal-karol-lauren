package com.movil.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movil.service.entidades.Movil;


@Repository
public interface MovilRepository extends JpaRepository<Movil, Integer>{

	List<Movil> findByUsuarioId(int usuarioId);
	
}