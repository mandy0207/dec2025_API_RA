package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import org.testng.annotations.Test;

import enums.ApiResources;
import io.restassured.http.ContentType;
import pojo.Book;
import utils.CreateSpec;
import utils.UniqueGenerator;

public class LearnSpecBuilders {

	
	@Test
	public void specBuilderTest() {
		Book book = new Book(UniqueGenerator.getBookName(), UniqueGenerator.getISbn(), UniqueGenerator.getAisle(), UniqueGenerator.getAuthorName());
		
		given().spec(CreateSpec.makeRequestSpec(ApiResources.baseUrl.getResource(), ContentType.JSON)).body(book).
		when().post(ApiResources.postBook.getResource()).then().spec(CreateSpec.makeResponseSpec(HttpURLConnection.HTTP_OK)).assertThat().
		body("Msg", equalTo("successfully added"));
		
		
	}
}
