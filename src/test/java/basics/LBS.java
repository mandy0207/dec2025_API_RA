package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import enums.ApiResources;
import io.restassured.RestAssured;
import payLoads.BookPayload;
import utils.JSONParser;


public class LBS {
	
	@Test
	public void addBook() {
	
		Faker faker= new Faker();
		String isbn= faker.internet().password();
		String aisle = faker.number().digits(5);
		
		RestAssured.baseURI="http://216.10.245.166";
	    String addBookresponse=given().log().all().header("Content-Type", "application/json").body(BookPayload.getAddBookPayload(isbn, aisle)).when().post(ApiResources.postBook.getResource())
	   .then().log().all().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("Msg", equalTo("successfully added")).extract().response().asString();
		System.out.println("My Response below :: "+addBookresponse);
		
	
		
		String actualMsg=JSONParser.getJsonParser(addBookresponse).get("Msg");
		String bookID= JSONParser.getJsonParser(addBookresponse).getString("ID");
		//TestNG way of making assertion
		 Assert.assertEquals(actualMsg, "successfully added");
		 
		 System.out.println("My Book ID : "+ bookID);
	
		 
		String getBookResponse= given().log().all().queryParam("ID", bookID).when().get(ApiResources.getBook.getResource()).then().log().all()
		 .assertThat().statusCode(HttpURLConnection.HTTP_OK).extract().response().asString();

		String responseISBN=(String) JSONParser.getJsonParser(getBookResponse).getList("isbn").get(0);
		String responseAISLE=(String) JSONParser.getJsonParser(getBookResponse).getList("aisle").get(0);
		
		
		String expectedBookID =responseISBN+responseAISLE;
		Assert.assertEquals(bookID,expectedBookID);
		
		//Delete Book
		given().log().all().body( BookPayload.deleteBookPayLoad(bookID)).when().delete(ApiResources.deleteBook.getResource()).then().log().all()
		.assertThat().statusCode(HttpURLConnection.HTTP_OK).body("msg", equalTo("book is successfully deleted"));
		
		
	}
	
	
}
