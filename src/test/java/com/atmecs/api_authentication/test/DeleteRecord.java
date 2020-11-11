package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRecord 
{
	@Test
	public void deleteRecord() throws MalformedURLException
	{
		String url = "https://sample-3a82.restdb.io/rest/restapi-testing/5faa80545d95e30700002a8d";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.auth().preemptive().oauth2("accessToken")
				.header("x-apikey", "b35c5b2a12e7cb38d9913ecdd8734006969f2")
				.contentType(ContentType.JSON).delete(new URL(url)).then().extract().response();
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 204);
		
		System.out.println("Status Code: "+statusCode);
		
	}
}
