package performingcrudstoringvalueincontext;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Get {

	
	@Test
	public void getUser(ITestContext context) {
		
		System.out.println("================================Get Users=====================================");

		int id = (Integer) context.getAttribute("user_id");
		
		Response res = given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88").pathParam("id", id)
		
		.when().get("https://gorest.co.in/public/v2/users/{id}");
		System.out.println(res.asPrettyString());
		
		
	}
}
