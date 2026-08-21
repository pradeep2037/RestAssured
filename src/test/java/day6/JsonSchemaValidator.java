package day6;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonSchemaValidator {
	
	@Test
	public void validatedJsonSchema() {
		
		given()
		
		
		.when().get("http://localhost:3000/book")
		
		
		.then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("booksJsonSchema.json"));
	}
}
