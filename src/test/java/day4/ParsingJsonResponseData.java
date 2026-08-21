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















//Correct understanding (step-by-step)
//🔹 Step 1: Initial response
//Response res = given().when().get(...);
//
//👉 This is:
//
//A Response object (Java object)
//It contains: status, headers, body
//🔹 Step 2: Convert to String
//String actualJsonFormat = res.asString();
//
//👉 Now you have:
//
//A String
//That string contains JSON text
//
//Example:
//
//[
//  { "title": "The Lord of the Rings" }
//]
//
//⚠️ Important:
//
//This is just plain text, not JSON objects yet in Java
//
//🔹 Step 3: Convert String → JSON structure
//JSONArray arr = new JSONArray(actualJsonFormat);
//
//👉 Now:
//
//String is parsed into JSONArray (Java object)
//Inside it → JSONObject(s)
//🔹 Step 4: Access value
//arr.getJSONObject(i).get("title");
//
//👉 Returns:
//
//Object type (Java treats all JSON values as Object)
//🔹 Step 5: Convert value → String
//.toString()

//===================================================================================================================
//Why not use asString() again?
//✅ asString() is only for Response object
//Response res = given().when().get(...);
//String body = res.asString();  // ✔ valid
//
//👉 Works because:
//
//res is a Response
//asString() is a method of Response class
//❌ But here you don’t have Response anymore
//arr.getJSONObject(i).get("title")
//
//👉 This returns:
//
//Object (not Response)
//
//So this will NOT work:
//
//arr.getJSONObject(i).get("title").asString(); // ❌ ERROR
//
//👉 Because:
//
//Object class has no asString() method
//✅ Why .toString() is used
//String bookTitle = arr.getJSONObject(i).get("title").toString();
//
//👉 Because:
//
//Returned type = Object
//We need String
//toString() is available for every Java object
//🧠 Simple rule
//Situation	Method to use
//Response → String	asString()
//Object → String	toString()
//===================================================================================================================
//🔥 What’s really happening
//🔹 1) Response → String (raw JSON text)
//String actualJsonFormat = res.asString();
//
//👉 Now you have:
//
//"[{ \"title\": \"The Lord of the Rings\" }]"
//This is just text
//You can’t access fields like title from plain text
//🔹 2) String → JSONArray (structured data)
//JSONArray arr = new JSONArray(actualJsonFormat);
//
//👉 Now you have:
//
//A Java JSON structure
//You can do:
//arr.getJSONObject(i)
//
//👉 Without this step, you cannot loop or access keys
