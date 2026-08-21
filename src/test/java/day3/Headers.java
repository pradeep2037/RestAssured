package day3;

import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class Headers {

	@Test
	public void testHeaders() {

		// Here verifying
		given()

				.when().get("https://www.google.com/")

				.then().header("Content-Type", "text/html; charset=ISO-8859-1").header("Content-Encoding", "gzip")
				.header("Server", "gws");
	}

	@Test
	public void getHeaders() {

		//Here we are trying to get the information from the headers and doing validations
		Response res = given()

				.when().get("https://www.google.com/");
		
		
		//To Get Single Header
		String singleHeader = res.getHeader("Content-Type");
		System.out.println("Single Header : "+singleHeader);
		
		
		//To Get Multiple Headers
	    io.restassured.http.Headers myheaders = res.getHeaders();
	    
	    for (Header header : myheaders) {
			
	    	System.out.println(header.getName()+"                "+header.getValue());
		}

	}
}
