package day2;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostRequestBodyUsingHashMap {

	int userId;

	@Test(priority = 1)
	public void creatingUserUsingHashMap() {

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "rohitsharma");
		data.put("gender", "male");
		data.put("email", "rohitsharma@gmail.com");
		data.put("status", "active");

//		String [] arr = {"c","java"};
//		data.put("courses", arr);

		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

				.when().post("https://gorest.co.in/public/v2/users")

				.then().statusCode(201).body("name", equalTo("rohitsharma"))
				.body("email", equalTo("rohitsharma@gmail.com")).log().all().extract().response();

		userId = res.jsonPath().getInt("id");
	}

	
	@Test(priority = 2, dependsOnMethods  = {"creatingUserUsingHashMap"})
	public void deleteTest() {

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

		.when().delete("https://gorest.co.in/public/v2/users/" +  userId)

		.then().statusCode(204).log().all();
	}
}
