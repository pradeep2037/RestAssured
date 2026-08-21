package day1;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.UUID;

public class HTTPRequests {

	int userId;

	@Test(priority = 1)
	public void getAllUsers() {

		System.out.println("================================Get Users=====================================");

//		------------------------------------------------------------------------------------------------------------------
		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

//		------------------------------------------------------------------------------------------------------------------
				.when().get("https://gorest.co.in/public/v2/users")

//		------------------------------------------------------------------------------------------------------------------
				.then().statusCode(200).log().all();
	}
	

//	===================================================================================================================================

	
	@Test(priority = 2)
	public void createUser() {
		
		System.out.println("================================Create User=====================================");

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "rohit458");
		data.put("gender", "male");
		data.put("email", "sanket" + UUID.randomUUID().toString() + "@gmail.com");
		data.put("status", "active");

//		------------------------------------------------------------------------------------------------------------------
		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

//		------------------------------------------------------------------------------------------------------------------
				.when().post("https://gorest.co.in/public/v2/users")

//		------------------------------------------------------------------------------------------------------------------
				.then().statusCode(201).log().all().extract().response();

		userId = res.jsonPath().getInt("id");
		
		System.out.println(userId);
	}

	
//	===================================================================================================================================

	
	@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {

		System.out.println("================================Update User=====================================");

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "rohitpop495");
		data.put("gender", "male");
		data.put("email", "rohitpop495@gmail.com");
		data.put("status", "inactive");

//		------------------------------------------------------------------------------------------------------------------
		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

//		------------------------------------------------------------------------------------------------------------------
				.when().put("https://gorest.co.in/public/v2/users/" + userId)

//		------------------------------------------------------------------------------------------------------------------
				.then().statusCode(200).log().all();
		
		System.out.println(userId);

	}
	
	
//	===================================================================================================================================

	
	@Test(priority = 4)
	public void deleteUser() {

		System.out.println("================================delete User=====================================");

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

				.when().delete("https://gorest.co.in/public/v2/users/" +  userId)

				.then().statusCode(204).log().all();
	}
}
