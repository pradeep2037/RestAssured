package requestpayload;

import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.UUID;

public class UsingPojoClass {

	@Test
	public void postingUsersUsingHashMap() {

		PojoClass data = new PojoClass();
		data.setEmail("rohit" + UUID.randomUUID().toString() + "@gmail.com");
		data.setGender("male");
		data.setName("rohit");
		data.setStatus("active");

		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data)

				.when().post("https://gorest.co.in/public/v2/users");
		System.out.println(res.asPrettyString());
	}
}
