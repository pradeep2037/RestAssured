package day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

//	"https://reqres.in/api/users?page=2"
	
	@Test
	public void validQueryParamOnly() {
	    given()
	        .pathParam("mypath", "users")
	        .queryParam("page", 2)
	    .when()
	        .get("https://reqres.in/api/{mypath}")
	    .then()
	        .statusCode(200)
	        .log().all();
	}

}
