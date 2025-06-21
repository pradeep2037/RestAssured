package day7;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class OAuth2Authentication {

	
	@Test
	public void OAuth1Authentication() {
		
		given().auth().oauth2(DEFAULT_BODY_ROOT_PATH)
		
		
		.when().get("https://gorest.co.in/public/v2/users")
		
		
		.then().statusCode(200).log().all();
	}
}
