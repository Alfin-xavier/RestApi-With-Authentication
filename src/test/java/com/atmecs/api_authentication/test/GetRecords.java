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

public class GetRecords 
{
	@Test
	public void getApiAuth() throws MalformedURLException
	{
		Logging log = new Logging();
		
		String requestUrl = "https://sample-3a82.restdb.io/rest/restapi-testing" ;
		
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";
		
		log.info("Building the request !!"+"\n");
		
		RequestSpecification request = RestAssured.given();
		
		log.info("Providing access to retrieve the records ang getting response !!"+"\n");
		
		Response response = (Response) request.auth().preemptive().oauth2(accessToken)
							.header("x-apikey", accessToken)
							.contentType(ContentType.JSON).get(new URL(requestUrl))
							.then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		String body = response.getBody().asString();
		
		log.info("Getting response body"+"\n");
		
		log.info("Response body: "+body);
		
		log.info("Getting status code");
		
		log.info("Verifying the status code !!");
		
		log.info("Status Code:"+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		log.info("Verified status code !!");
		
	}
}
