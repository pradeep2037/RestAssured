package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	public void dummyData() {
		
		
		Faker fake = new Faker();
		
		String fullName = fake.name().fullName();
		String firstName = fake.name().firstName();
		String lastName = fake.name().lastName();
		
		String username = fake.name().username();
		String password = fake.internet().password();
		
		String phoneNo = fake.phoneNumber().cellPhone();
		String email = fake.internet().safeEmailAddress();
		
		System.out.println("Full Name : "+fullName);
		System.out.println("First Name : "+firstName);
		System.out.println("Last Name : "+lastName);
		System.out.println("Username : "+username);
		System.out.println("Password : "+password);
		System.out.println("Phone Number : "+phoneNo);
		System.out.println("Email : "+email);

	}
}
