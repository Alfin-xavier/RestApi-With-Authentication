package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_authentication.utility.UsersDataProvider;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateRecord
{
	@Test(dataProvider = "createUser", dataProviderClass = UsersDataProvider.class )
	public void updateUser(Object requestBody) throws MalformedURLException
	{
		String url = "https://sample-3a82.restdb.io/rest/restapi-testing/5fab80035d95e30700003985";
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");

		RequestSpecification request = RestAssured.given().headers(headers);

		Response response = request.auth().preemptive().oauth2("accessToken")
				.header("x-apikey", "b35c5b2a12e7cb38d9913ecdd8734006969f2")
				.body(requestBody.toString())
				.contentType(ContentType.JSON)
				.put(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();

		System.out.println("Status Code:" + statusCode);
		System.out.println("Response Body:" + responseBody);

		Assert.assertEquals(statusCode, 200);

		JsonPath jsonPath = response.jsonPath();

		String firstname = jsonPath.getString("firstname");
		System.out.println("FirstName:" + firstname);
		
		String lastname = jsonPath.getString("lastname");
		System.out.println("LastName:" + lastname);
		
		String designation = jsonPath.getString("designation");
		System.out.println("Name:" + designation);
		
		JSONObject jsonObject = (JSONObject) requestBody;
		Assert.assertEquals(firstname, jsonObject.get("firstname").toString());

		String updatedAt = jsonPath.getString("_created");
		System.out.println("Updated:" + updatedAt);

	}
}
