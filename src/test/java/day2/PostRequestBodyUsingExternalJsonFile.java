package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.internal.support.FileReader;
import io.restassured.response.Response;

public class PostRequestBodyUsingExternalJsonFile {
	
int userId;
	
	@Test(priority = 1)
	public void creatingUserUsingExternalFile() throws FileNotFoundException {

	    File file = new File(".\\body.json");
	    
	    java.io.FileReader fr = new java.io.FileReader(file);
	    
	    JSONTokener jt = new JSONTokener(fr);
	    
	    JSONObject data = new JSONObject(jt);
	    
	    //The server expects whenever we send data the data must be in json string format that's why we convert data into string

		Response res = given()
				.header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")
				.header("Content-Type", "application/json").body(data.toString())

				.when().post("https://gorest.co.in/public/v2/users")

				.then().statusCode(201).body("name", equalTo("rohitsharma"))
				.body("email", equalTo("rohitsharma@gmail.com")).log().all().extract().response();

		userId = res.jsonPath().getInt("id");
	}


	
	@Test(priority = 2,dependsOnMethods = {"creatingUserUsingExternalFile"})
	public void deleteTest() {

		given().header("Authorization", "Bearer bcf44d05ff8c8bbae216ab0bc329ff5be8a694bd6b1053eb2e6379f19a47cc88")

		.when().delete("https://gorest.co.in/public/v2/users/" +  userId)

		.then().statusCode(204).log().all();
	}
}
