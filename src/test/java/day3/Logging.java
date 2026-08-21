package day3;


import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;


public class Logging {

	@Test
	public void logging() {
		
		given()
		
		.when().get("https://reqres.in/api/users?page=2")
		
		.then().log().body();
		System.out.println("-------------------------------------");
		
//		.log()headers()
//		.log()cookies()
//		.log().all()

	}
}
