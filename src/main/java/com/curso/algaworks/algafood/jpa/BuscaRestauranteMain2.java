package com.curso.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.curso.algaworks.algafood.AlgafoodApiApplication;
import com.curso.algaworks.algafood.domain.model.Restaurante;
import com.curso.algaworks.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain2 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository= applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		System.out.println("Nome: " + restaurante.getNome() + " - Frente: " + restaurante.getTaxaFrete());
	}
}
