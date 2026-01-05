package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import org.testng.annotations.Test;

import enums.ApiResources;
import io.restassured.RestAssured;
import pojo.Book;
import utils.UniqueGenerator;

public class LearnSerialization {

	@Test
	public void serializationTest() {
		Book book = new Book(UniqueGenerator.getBookName(), UniqueGenerator.getISbn(), UniqueGenerator.getAisle(), UniqueGenerator.getAuthorName());
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type", "application/json").body(book).when().post(ApiResources.postBook.getResource())
	   .then().log().all().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("Msg", equalTo("successfully added")).extract().response().asString();
	
		
	}
}
