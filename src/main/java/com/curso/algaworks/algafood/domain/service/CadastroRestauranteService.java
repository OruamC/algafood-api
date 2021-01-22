package com.curso.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.curso.algaworks.algafood.domain.model.Cozinha;
import com.curso.algaworks.algafood.domain.model.Restaurante;
import com.curso.algaworks.algafood.domain.repository.CozinhaRepository;
import com.curso.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}
		
		return restauranteRepository.salvar(restaurante);
	}
	
}
