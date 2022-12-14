package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.modelos.Movil;

@FeignClient(name = "movil-service",url = "http://localhost:8003")
@RequestMapping("/movil")
public interface MovilFeignClient {
	@PostMapping()
	public Movil save(@RequestBody Movil moto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Movil> getMovils(@PathVariable("usuarioId") int usuarioId);

}
