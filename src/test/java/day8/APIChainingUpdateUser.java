package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

public class APIChainingUpdateUser {

	@Test
	public void updateUser(ITestContext context) {

		Faker fake = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", fake.name().fullName());
		data.put("gender", "male");
		data.put("email", fake.internet().emailAddress());
		data.put("status", "inactive");

		int id = (Integer) context.getAttribute("user_id");

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data.toString()).pathParam("id", id)

				.when().put("https://gorest.co.in/public/v2/users/{id}")

				.then().statusCode(200).log().all();

	}

}
