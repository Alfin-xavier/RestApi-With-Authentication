package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_authentication.utility.Logging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRecord 
{
	@Test
	public void deleteRecord() throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url = "https://sample-3a82.restdb.io/rest/restapi-testing/5fad0f69fdcc931600001432";
		
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";
		
		RequestSpecification request = RestAssured.given();
		
		log.info("Providing access to delete record from db !!"+"\n");
		Response response = request.auth().preemptive().oauth2(accessToken)
				.header("x-apikey", accessToken)
				.contentType(ContentType.JSON).delete(new URL(url)).then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		log.info("Getting status code: "+ statusCode);
		
		log.info("Verifying status code !!");
		Assert.assertEquals(statusCode, 204);
		
		log.info("Verified status code !!"+"\n");
		
		
	}
}
