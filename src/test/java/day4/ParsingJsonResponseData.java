package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingJsonResponseData {
	
	
	//Approach 1
//	@Test(priority = 1)
	public void validatingResponseData() {
		
		given().contentType(ContentType.JSON)
		
		.when().get("http://localhost:3000/book")
		
		.then().statusCode(200).header("Content-Type", "application/json").body("[3].title", equalTo("The Lord of the Rings"));
	}
	
	
	//Approach2
//	@Test(priority = 2)
	public void validatingResponseData2() {
		
		Response res = given().contentType(ContentType.JSON)
		
		.when().get("http://localhost:3000/book");
		
		int statusCode = res.getStatusCode();
		
		String header = res.getHeader("Content-Type");
				
		Assert.assertEquals(statusCode, 200);     //validation 1
		Assert.assertEquals(header, "application/json");
		
		String bookName = res.jsonPath().get("[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");
	}
	
	
	
    //Searching for the title of the book in json
	@Test(priority = 3)
	public void validatingResponseData3() {
		
		Response res = given().contentType(ContentType.JSON)
		
		.when().get("http://localhost:3000/book");
		
		String actualJsonFormat = res.asString();
		
		JSONArray arr = new JSONArray(actualJsonFormat);
		
	    boolean status = false;
		
		for(int i=0;i<arr.length();i++){
			
//Now arr.getJSONObject(i).get("title") returns an Object, not a String — even though we know it's a string value in the JSON.
//So we use .toString() to safely convert the value to string before using it in .equals().
			
			String bookTitle = arr.getJSONObject(i).get("title").toString();
			
			if(bookTitle.equals( "The Lord of the Rings")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	}
	
	
	//Validating total price of books
//	@Test(priority = 4)
	public void validatingResponseData4() {
		
		Response res = given().contentType(ContentType.JSON)
		
		.when().get("http://localhost:3000/book");
		
		String actualJsonFormat = res.asString();
		
		JSONArray arr = new JSONArray(actualJsonFormat);
				
		double TotalPrice = 0;
		
		for(int i=0;i<arr.length();i++){
			
			String price = arr.getJSONObject(i).get("price").toString();
			
			
			TotalPrice = TotalPrice + Double.parseDouble(price);
			
		}
		
		Assert.assertEquals(TotalPrice, 526);
	}
		
}
