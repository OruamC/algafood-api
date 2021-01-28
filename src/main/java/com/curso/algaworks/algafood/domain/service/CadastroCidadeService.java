package com.curso.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.curso.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.curso.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.curso.algaworks.algafood.domain.model.Cidade;
import com.curso.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade Cidade) {
		return cidadeRepository.save(Cidade);
	}
	
	public void excluir(Long CidadeId) {
		try {
			cidadeRepository.deleteById(CidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Cidade com código %d", CidadeId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de Código %d não pode ser removida, pois está em uso", CidadeId));
		}
	}
}
