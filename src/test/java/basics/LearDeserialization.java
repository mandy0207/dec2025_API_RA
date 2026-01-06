package basics;

import static io.restassured.RestAssured.given;

import java.net.HttpURLConnection;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.ApiResources;
import io.restassured.RestAssured;
import pojo.Book;

public class LearDeserialization {

	@Test
	public void mydesrialisedTest() throws JsonMappingException, JsonProcessingException {
		RestAssured.baseURI="http://216.10.245.166";
		String getBookResponse= given().log().all().queryParam("ID", "2g1u303o534202").when().get(ApiResources.getBook.getResource()).then().log().all()
				 .assertThat().statusCode(HttpURLConnection.HTTP_OK).extract().response().asString();
		ObjectMapper mapper = new ObjectMapper();
		Book[] bookArray = mapper.readValue(getBookResponse, Book[].class);
		
		for(Book book : bookArray) {
			System.out.println(book.getName());
			System.out.println(book.getAuthor());
			
		}
		
	}
}
