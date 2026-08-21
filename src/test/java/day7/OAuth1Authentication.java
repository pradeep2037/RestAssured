package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class OAuth1Authentication {
	
	
	@Test
	public void oauth1Authentication() {
		
		given().auth().oauth("consumerkey", "consumerSecrat", "accessToken", "tokenSecrate") //This is for OAuth1 Authentication
		
		.when().get("url")
		
		.then().statusCode(200).log().all();
	}
}
