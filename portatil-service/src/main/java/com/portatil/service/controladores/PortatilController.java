package com.portatil.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portatil.service.entidades.Portatil;

import com.portatil.service.servicios.PortatilService;


@RestController
@RequestMapping("/portatil")
public class PortatilController {

	@Autowired
	private PortatilService portatilService;
	
	@GetMapping
	public ResponseEntity<List<Portatil>> listarportatils(){
		List<Portatil> portatils = portatilService.getAll();
		if(portatils.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(portatils);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Portatil> obtenerportatil(@PathVariable("id") int id){
		Portatil portatil = portatilService.getportatilById(id);
		if(portatil == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(portatil);
	}
	
	@PostMapping
	public ResponseEntity<Portatil> guardarportatil(@RequestBody Portatil portatil){
		Portatil nuevoportatil = portatilService.save(portatil);
		return ResponseEntity.ok(nuevoportatil);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Portatil>> listarportatilsPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Portatil> portatils = portatilService.byUsuarioId(id);
		if(portatils.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(portatils);
	}
}