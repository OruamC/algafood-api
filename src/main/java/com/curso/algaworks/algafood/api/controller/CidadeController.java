package com.curso.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.curso.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.curso.algaworks.algafood.domain.model.Cidade;
import com.curso.algaworks.algafood.domain.repository.CidadeRepository;
import com.curso.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository CidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<Cidade> listar(){
		return CidadeRepository.listar();
	}
	
	@GetMapping(path = "/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return CidadeRepository.buscar(cidadeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
	}
	
	@PutMapping(path = "/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = CidadeRepository.buscar(cidadeId);
		
		if(cidadeAtual != null) {
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			
			cidadeAtual = CidadeRepository.salvar(cidadeAtual);
			
			return ResponseEntity.ok(cidadeAtual);
		}
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping(path = "/{cidadeId}")
	public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
		try {
			cadastroCidade.excluir(cidadeId);
			
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();	
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
