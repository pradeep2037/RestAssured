package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class APIKeyAuthentication {

	@Test
	public void apiKeyAuth() {
		
		given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")  //appid is APIKey
		
		
		.when().get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&Units=metric&cnt=7")
		
		
		.then().statusCode(200).log().all();
	}
}
