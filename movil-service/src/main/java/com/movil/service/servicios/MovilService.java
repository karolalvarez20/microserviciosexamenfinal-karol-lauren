package com.movil.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movil.service.entidades.Movil;
import com.movil.service.repositorio.MovilRepository;


@Service
public class MovilService {

	@Autowired
	private MovilRepository movilRepository;

	public List<Movil> getAll() {
		return movilRepository.findAll();
	}

	public Movil getmovilById(int id) {
		return movilRepository.findById(id).orElse(null);
	}

	public Movil save(Movil movil) {
		Movil nuevamovil = movilRepository.save(movil);
		return nuevamovil;
	}

	public List<Movil> byUsuarioId(int usuarioId) {
		return movilRepository.findByUsuarioId(usuarioId);
	}
}
