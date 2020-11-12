package com.atmecs.api_authentication.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_authentication.utility.Logging;
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
		Logging log = new Logging();
		
		String url = "https://sample-3a82.restdb.io/rest/restapi-testing/5fad0f69fdcc931600001432";
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";
		
		RequestSpecification request = RestAssured.given();

		log.info("Providing access and modifying record !!"+"\n");
		Response response = request.auth().preemptive().oauth2(accessToken)
				.header("x-apikey", "b35c5b2a12e7cb38d9913ecdd8734006969f2")
				.contentType(ContentType.JSON).when().body(requestBody.toString())
							.patch(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();

		log.info("Getting status code: "+ statusCode);
		
		log.info("Verifying status code !!");
		Assert.assertEquals(statusCode, 200);
		log.info("Verified status code !!"+"\n");
		
		log.info("Getting response body: "+ responseBody+"\n");

		JsonPath jsonPath = response.jsonPath();

		String id = jsonPath.getString("_id");
		log.info("Id: " + id);
		
		String firstname = jsonPath.getString("firstname");
		log.info("Firstname: " + firstname);
		
		String lastname = jsonPath.getString("lastname");
		log.info("LastName: " + lastname);
		
		String designation = jsonPath.getString("designation");
		log.info("Designation:" + designation);
		
		String mail = jsonPath.getString("mail");
		log.info("Mail:" + mail);
		
		String createdAt = jsonPath.getString("_created");
		log.info("Getting createdAt:" + createdAt);

	}
}
