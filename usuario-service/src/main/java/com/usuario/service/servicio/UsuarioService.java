package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignclients.PortatilFeignClient;
import com.usuario.service.feignclients.MovilFeignClient;
import com.usuario.service.modelos.Portatil;
import com.usuario.service.modelos.Movil;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	@Autowired
	private RestTemplate RestTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PortatilFeignClient portatilFeignClient;
	
	@Autowired
	private MovilFeignClient movilFeignClient;
	
	
	public List<Portatil> getportatils(int usuarioId){
		List<Portatil> portatils= RestTemplate.getForObject("http://localhost:8002/portatil/usuario/"+ usuarioId,List.class);
		return portatils;
	}
	
	public List<Movil> getmovils(int usuarioId){
		List<Movil> movils= RestTemplate.getForObject("http://localhost:8003/movil/usuario/"+ usuarioId,List.class);
		return movils;
	}
	
	public Portatil saveCarro(int usuarioId, Portatil portatil) {
		portatil.setUsuarioId(usuarioId);
		Portatil nuevoCarro = portatilFeignClient.save(portatil);
		return nuevoCarro;
	}
	
	public List<Usuario> getAll(){
	 return  usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);	
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
	public Portatil savePortatil(int usuarioId, Portatil portatil) {
		portatil.setUsuarioId(usuarioId);
		Portatil nuevoPortatil = portatilFeignClient.save(portatil);
		return nuevoPortatil;
	}
	
	public Movil saveMovil(int usuarioId,Movil movil) {
		movil.setUsuarioId(usuarioId);
		Movil nuevaMovil = movilFeignClient.save(movil);
		return nuevaMovil;
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
		Map<String,Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		
		if(usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario",usuario);
		List<Portatil> portatils = portatilFeignClient.getPortatils(usuarioId);
		if(portatils.isEmpty()) {
			resultado.put("Portatils", "El usuario no tiene Portatils");
		}
		else {
			resultado.put("Portatils", portatils);
		}
		
		List<Movil> movils = movilFeignClient.getMovils(usuarioId);
		if(movils.isEmpty()) {
			resultado.put("Movils", "El usuario no tiene motos");
		}		
		else {
			resultado.put("Movils", movils);
		}
		return resultado;
	}
	
	
	
}