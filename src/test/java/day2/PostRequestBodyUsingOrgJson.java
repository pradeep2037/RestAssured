package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.response.Response;

public class PostRequestBodyUsingOrgJson {
	
	
	int userId;
	
	@Test(priority = 1)
	public void creatingUserUsingJsonLibrary() {

	    JSONObject data = new JSONObject();

		data.put("name", "rohitsharmas");
		data.put("gender", "male");
		data.put("email", "rohitsharmas@gmail.com");
		data.put("status", "active");


		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data.toString())

				.when().post("https://gorest.co.in/public/v2/users")

				.then().statusCode(201).body("name", equalTo("rohitsharmas"))
				.body("email", equalTo("rohitsharmas@gmail.com")).log().all().extract().response();

		userId = res.jsonPath().getInt("id");
	}


	
	@Test(priority = 2,dependsOnMethods = {"creatingUserUsingJsonLibrary"})
	public void deleteTest() {

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

		.when().delete("https://gorest.co.in/public/v2/users/" +  userId)

		.then().statusCode(204).log().all();
	}
}
