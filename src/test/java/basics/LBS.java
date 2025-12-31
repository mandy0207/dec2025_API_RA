package basics;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import payLoads.BookPayload;



public class LBS {
	
	@Test
	public void addBook() {
	
		Faker faker= new Faker();
		String isbn= faker.internet().password();
		String aisle = faker.name().firstName();
		
		RestAssured.baseURI="http://216.10.245.166";
	   String response=given().log().all().header("Content-Type", "application/json").body(BookPayload.getAddBookPayload(isbn, aisle)).when().post("/Library/Addbook.php")
	   .then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("My Response below :: "+response);
		
		
		
	}
	
	
}
