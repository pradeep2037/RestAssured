package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class APIChainingCreateUser {

	@Test
	public void createUser(ITestContext context) {

		int id;
		Faker fake = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", fake.name().fullName());
		data.put("gender", "male");
		data.put("email", fake.internet().emailAddress());
		data.put("status", "active");

		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data.toString())

				.when().post("https://gorest.co.in/public/v2/users");

		id = res.jsonPath().get("id");
		System.out.println("Generated id is : "+id);
		context.setAttribute("user_id", id);
	}

}
