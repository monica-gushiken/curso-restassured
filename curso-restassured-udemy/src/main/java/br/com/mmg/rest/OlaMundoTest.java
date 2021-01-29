package br.com.mmg.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

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
			.get("http://restapi.wcaquino.me/ola")
		.then() // Assertivas
			.statusCode(200);
		
	}
}
