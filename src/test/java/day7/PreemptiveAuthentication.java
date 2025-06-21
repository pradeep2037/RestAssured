package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class PreemptiveAuthentication {

	@Test
	public void preemptiveAuthentication() {
		
		given().auth().preemptive().basic("postman", "password")
		
		.when().get("https://postman-echo.com/basic-auth")
		
		
		.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
}
