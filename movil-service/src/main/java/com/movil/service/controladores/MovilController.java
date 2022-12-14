package com.movil.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movil.service.entidades.Movil;
import com.movil.service.servicios.MovilService;

@RestController
@RequestMapping("/movil")
public class MovilController{

	@Autowired
	private MovilService movilService;
	
	@GetMapping
	public ResponseEntity<List<Movil>> listarmovils(){
		List<Movil> movils = movilService.getAll();
		if(movils.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(movils);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movil> obtenermovil(@PathVariable("id") int id){
		Movil movil = movilService.getmovilById(id);
		if(movil == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(movil);
	}
	
	@PostMapping
	public ResponseEntity<Movil> guardarmovil(@RequestBody Movil movil){
		Movil nuevamovil = movilService.save(movil);
		return ResponseEntity.ok(nuevamovil);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Movil>> listarmovilsPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Movil> movils = movilService.byUsuarioId(id);
		if(movils.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(movils);
	}
	
}
