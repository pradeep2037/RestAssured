package day3;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class Cookies {
	
	
	@Test
	public void testCookies() {
		
		Response res = given()
		
		.when().get("https://www.google.com/");
		
		//To Get Single Cookie
		String singleCookie = res.getCookie("AEC");
		System.out.println("Single Cookie : "+singleCookie);
		
		
		//To Get All Cookies
		Map<String, String> allCookies = res.getCookies();
		System.out.println(allCookies);
		
		//A Map is not directly iterable using an enhanced for loop like for(String item : map). You must explicitly choose what part of the map you want to iterate:
		
		for (String cookie : allCookies.keySet()) {
			
			String val = res.getCookie(cookie);
			System.out.println(cookie+"     "+val);
		}
	}
}
