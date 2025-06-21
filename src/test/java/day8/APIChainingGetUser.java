package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;



public class APIChainingGetUser {

	@Test
	public void getUser(ITestContext context) {
		
		int id = (Integer) context.getAttribute("user_id");  //This should come from create user request
		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88").pathParam("id", id)

		
		.when().get("https://gorest.co.in/public/v2/users/{id}")
		
		.then().statusCode(200).log().all();
	}
}
