package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRecords 
{
	@Test
	public void getApiAuth() throws MalformedURLException
	{
		String requestUrl = "https://sample-3a82.restdb.io/rest/restapi-testing" ;
		
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = (Response) request.auth().preemptive().oauth2(accessToken)
							.header("x-apikey", "b35c5b2a12e7cb38d9913ecdd8734006969f2")
							.contentType(ContentType.JSON).get(new URL(requestUrl))
							.then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		String body = response.getBody().asString();
		
		System.out.println(body);
		
		System.out.println("Status Code:"+statusCode);

		Assert.assertEquals(statusCode, 200);
	}
}
