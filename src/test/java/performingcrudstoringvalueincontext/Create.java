package performingcrudstoringvalueincontext;

import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import com.github.javafaker.Faker;

public class Create {

	private Faker fakeData;

	@Test
	public void createUser(ITestContext context) {
		
		System.out.println("================================Post User=====================================");

		JSONObject data = new JSONObject();
		fakeData = new Faker();
	
		data.put("name", fakeData.name().fullName());
		data.put("gender", "male");
		data.put("email", fakeData.internet().emailAddress());
		data.put("status", "active");
		
		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data.toString())
		
		.when().post("https://gorest.co.in/public/v2/users");
		
		System.out.println(res.asPrettyString());
		
		int id = res.jsonPath().getInt("id");
		
		context.setAttribute("user_id", id);
		
		
	}
}
