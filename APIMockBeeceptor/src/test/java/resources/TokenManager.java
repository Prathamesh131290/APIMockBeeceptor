package resources;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager 
{
	private static String accessToken;
	private static long expiryTime;
	
	public static String getToken() throws IOException
	{
		if (accessToken == null || System.currentTimeMillis() > expiryTime)
		{
			generateNewToken();
		}
		return accessToken;
	}

	private static void generateNewToken() throws IOException 
	{
		Response response = RestAssured
				.given()
				.baseUri(Utils.getGlobalValue("baseUrl"))
				.header("Content-Type","application/json")
				.body("payload") //payoad here
				.post("apiroute"); //api route here
		accessToken = response.jsonPath().getString("access_token");
		int expiresIn = response.jsonPath().getInt("expires_in");
		expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
	}
}
