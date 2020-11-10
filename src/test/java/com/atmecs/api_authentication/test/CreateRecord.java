package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_authentication.utility.UsersDataProvider;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateRecord 
{
	@Test(dataProvider = "createUser", dataProviderClass = UsersDataProvider.class )
	public void createRecord(Object requestBody) throws MalformedURLException
	{
			String url ="https://sample-3a82.restdb.io/rest/restapi-testing";
			
			RequestSpecification request = RestAssured.given();
			
			Response response = request.when().body(requestBody.toString())
								.post(new URL(url)).then().extract().response();
			
			int statusCode = response.getStatusCode();
			String responseBody = response.getBody().asString();
			
			System.out.println("Status Code:"+ statusCode);
			System.out.println("Response Body:"+ responseBody);
			
			
			  Assert.assertEquals(statusCode, 201);
			  
			  JsonPath jsonPath = response.jsonPath();

			  String id = jsonPath.getString("id"); 
			  System.out.println("Id:"+id);
			  
			  String createdAt = jsonPath.getString("createdAt"); 
			  System.out.println("CreatedAt:"+createdAt);
			  		 
	}
}
