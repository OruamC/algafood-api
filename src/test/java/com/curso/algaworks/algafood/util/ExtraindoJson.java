package com.curso.algaworks.algafood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

public class ExtraindoJson {

	public static String getContentFromResource(String resource) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resource);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
