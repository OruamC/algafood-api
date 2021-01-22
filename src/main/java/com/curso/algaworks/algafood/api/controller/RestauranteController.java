package com.curso.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.algaworks.algafood.domain.model.Restaurante;
import com.curso.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

@RestController
@RequestMapping(path = "/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepositoryImpl restauranteRepositoryImpl;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepositoryImpl.listar();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteRepositoryImpl.buscar(restauranteId);
		
		if(restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		
		return ResponseEntity.notFound().build();
	}
}
