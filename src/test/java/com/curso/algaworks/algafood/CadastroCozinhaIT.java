package com.curso.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.curso.algaworks.algafood.domain.model.Cozinha;
import com.curso.algaworks.algafood.domain.repository.CozinhaRepository;
import com.curso.algaworks.algafood.util.DatabaseCleaner;
import com.curso.algaworks.algafood.util.ExtraindoJson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private static final int COZINHA_ID_INEXISTENTE = 100;
	
	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
		
		jsonCorretoCozinhaChinesa = ExtraindoJson.getContentFromResource("/json/correto/cozinha-chinesa.json");
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(quantidadeCozinhasCadastradas));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorreto_QuandoConsultarCozinhaExistente() {
		given()
		.pathParam("cozinhaId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarRespostaEStatus404_QuandoConsultarCozinhaInexistente() {
		given()
		.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha(); 
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		cozinhaAmericana = new Cozinha(); 
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);
		
		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}

}
