package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_authentication.utility.UsersDataProvider;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchRecord 
{
	@Test(dataProvider = "createUser", dataProviderClass = UsersDataProvider.class )
	public void updateUser(Object requestBody) throws MalformedURLException
	{
		String url = "https://sample-3a82.restdb.io/rest/restapi-testing";

		RequestSpecification request = RestAssured.given();

		Response response = request.auth().preemptive().oauth2("accessToken")
				.header("x-apikey", "b35c5b2a12e7cb38d9913ecdd8734006969f2")
				.contentType(ContentType.JSON).when().body(requestBody.toString())
							.patch(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();

		System.out.println("Status Code:" + statusCode);
		System.out.println("Response Body:" + responseBody);

		Assert.assertEquals(statusCode, 200);

		JsonPath jsonPath = response.jsonPath();

		String updatedAt = jsonPath.getString("updatedAt");
		System.out.println("CreatedAt:" + updatedAt);

	}
}
