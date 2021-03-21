package br.com.mmg.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status deveria ser 200", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());

		/*
		 * Quando h� uma exce��o antes do teste, d� Errors:1 e Failures:0 Quando o teste
		 * � executado e n�o passa, d� Errors:0 e Failures:1
		 */

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}

	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

		// Requisi��o simplificada
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);

		// Modo fluente - given when then
		given() // Pr�-condi��es
				.when() // A��o
				.get("http://restapi.wcaquino.me/ola").then() // Assertivas
				.statusCode(200);
	}

	@Test
	public void devoConhecerMatchersHamcrest() {
		assertThat("Maria", is("Maria"));
		assertThat("Maria", isA(String.class));
		assertThat(128d, isA(Double.class));
		assertThat(128d, greaterThan(120d));
		assertThat(128d, lessThan(130d));

		List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1, 3, 5, 7, 9));
		assertThat(impares, containsInAnyOrder(1, 3, 5, 9, 7));
		assertThat(impares, hasItem(1));
		assertThat(impares, hasItems(1, 5));
		
		assertThat("Maria", is(not("Jo�o")));
		assertThat("Maria", not("Jo�o"));
		assertThat("Maria", anyOf(is("Jo�o"),is("Maria")));
		
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
	}
	
	@Test
	public void devoValidarBody() {
		given() 
		.when() 
			.get("http://restapi.wcaquino.me/ola")
		.then() 
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())));
	}
}
