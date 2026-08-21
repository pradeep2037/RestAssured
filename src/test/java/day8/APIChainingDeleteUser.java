package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class APIChainingDeleteUser {

	@Test
	public void updateUser(ITestContext context) {

		int userId = (Integer) context.getAttribute("user_id");
		

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").pathParam("id", userId)

				.when().delete("https://gorest.co.in/public/v2/users/{id}")

				.then().statusCode(204).log().all();

	}

}
