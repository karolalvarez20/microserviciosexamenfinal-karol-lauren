package com.usuario.service.controlador;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Movil;
import com.usuario.service.modelos.Portatil;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/portatils/{usuarioId}")
	public ResponseEntity<List<Portatil>> listarportatils(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Portatil> portatils = usuarioService.getportatils(id);
		return ResponseEntity.ok(portatils);
		}
	
	@GetMapping("/movils/{usuarioId}")
	public ResponseEntity<List<Movil>> listarmovils(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Movil> movils = usuarioService.getmovils(id);
		return ResponseEntity.ok(movils);
	}
	
	@PostMapping("/portatil/{usuarioId}")
	public ResponseEntity<Portatil> guardarPortatil(@PathVariable("usuarioId") int usuarioId,@RequestBody Portatil portatil){
		Portatil nuevoPortatil = usuarioService.savePortatil(usuarioId, portatil);
		return ResponseEntity.ok(nuevoPortatil);
	} 
	
	@PostMapping("/movil/{usuarioId}")
	public ResponseEntity<Movil> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Movil movil){
		Movil nuevaMovil = usuarioService.saveMovil(usuarioId, movil);
		return ResponseEntity.ok(nuevaMovil);
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
		Map<String,Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}
		
}