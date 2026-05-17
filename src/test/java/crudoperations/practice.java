package crudoperations;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.UUID;

public class practice {

	int userId;

	@Test(priority = 1)
	public void getUsers() {

		System.out.println("================================Get Users=====================================");

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

				.when().get("https://gorest.co.in/public/v2/users")

				.then().statusCode(200).log().body(); // To print only json body

//		.then().statusCode(200).log().all();  Then mainly used for validation		
//		.log().all(), which prints everything (status line, headers, body, etc.). That’s why you’re seeing all the extra details.

	}

	@Test(priority = 2)
	public void createUser() {

		System.out.println("================================Post Users=====================================");
		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "sanket");
		data.put("gender", "male");
		data.put("email", "sanket" + UUID.randomUUID().toString() + "@gmail.com");
		data.put("status", "active");

		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

				.when().post("https://gorest.co.in/public/v2/users");

		userId = res.jsonPath().getInt("id");

		System.out.println(res.asPrettyString());
		System.out.println(res.jsonPath().getInt("id"));
		System.out.println(userId);

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {
		
		System.out.println("================================Update User=====================================");

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "priya");
		data.put("gender", "female");
		data.put("email", "priya" + UUID.randomUUID().toString() + "@gmail.com");
		data.put("status", "active");

		Response res = given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

				.when().put("https://gorest.co.in/public/v2/users/"+userId);
		System.out.println(res.asPrettyString());

	}
	
	
	@Test(priority = 4)
	public void deleteUser() {
		
		System.out.println("================================delete User=====================================");

		Response res = given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
		.header("Content-Type", "application/json")
		
		.when().delete("https://gorest.co.in/public/v2/users/"+userId);
		
		System.out.println(res.asPrettyString());
		
		
	}
}
