package com.curso.algaworks.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

	/*
	 * Permite configurar o spring para usar dentro de messages.properties as mensagens padroes de javax
	 * e tambem permite poder customiza-las. (Aula 9.14)
	 * O indicado é utlizar o messages.properties do proprio spring e realizar as customizações. 
	 */
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}
}
