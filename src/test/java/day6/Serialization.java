package day6;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.PojoClass;


public class Serialization {
	
	@Test
	public void validateSerialization() throws JsonProcessingException {
		
		//Created java object using pojo class
		PojoClass data = new PojoClass();
		
		data.setName("rohitsharma");
		data.setGender("male");
		data.setEmail("rohitsharma@gmail.com");
		data.setStatus("active");
		
		//Convert java object --> json object (serialization)
		
		ObjectMapper objMap= new ObjectMapper();
		
		String jsonData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.println(jsonData);
	
	}

}
