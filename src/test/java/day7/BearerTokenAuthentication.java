package day7;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class BearerTokenAuthentication {
	
	@Test
	public void bearerTokenAuth() {
		
		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
		
		.when().get("https://gorest.co.in/public/v2/users")

		.then().statusCode(200).log().all();


	}
}
