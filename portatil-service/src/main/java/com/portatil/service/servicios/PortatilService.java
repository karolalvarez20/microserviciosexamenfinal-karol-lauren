package com.portatil.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portatil.service.entidades.Portatil;
import com.portatil.service.reposirorio.PortatilRepository;




@Service
public class PortatilService {

	@Autowired
	private PortatilRepository portatilRepository;
	
	public List<Portatil> getAll(){
		return portatilRepository.findAll();
	}
	
	public Portatil getportatilById(int id) {
		return portatilRepository.findById(id).orElse(null);
	}
	
	public Portatil save(Portatil portatil) {
		Portatil nuevoportatil = portatilRepository.save(portatil);
		return nuevoportatil;
	}
	
	public List<Portatil> byUsuarioId(int usuarioId){
		return portatilRepository.findByUsuarioId(usuarioId);
	}
}
