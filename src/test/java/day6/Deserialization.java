package day6;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.PojoClass;

public class Deserialization {

	@Test
	public void validateDeserialization() throws JsonProcessingException {

		String jsonData = "{\r\n"
				+ "  \"name\" : \"rohitsharma\",\r\n"
				+ "  \"gender\" : \"male\",\r\n"
				+ "  \"email\" : \"rohitsharma@gmail.com\",\r\n"
				+ "  \"status\" : \"active\"\r\n"
				+ "}";

		// Convert json object --> java object (Deserialization)
		ObjectMapper objMap = new ObjectMapper();

		PojoClass javaData = objMap.readValue(jsonData, PojoClass.class);
		

		System.out.println("name : "+javaData.getName());
		System.out.println("gender :"+javaData.getGender());
		System.out.println("email :"+javaData.getEmail());
		System.out.println("status :"+javaData.getStatus());

	}
}
