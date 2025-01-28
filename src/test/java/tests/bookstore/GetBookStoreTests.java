package tests.bookstore;

import api.bookstore.BookStoreAPI;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetBookStoreTests extends BaseTest {

    @Test
    public void testGetAllBooks() {
        Response response = BookStoreAPI.getAllBooks();
        System.out.println("RESPONSE: " + response.asPrettyString());
        assertEquals(200, response.statusCode(), "Request failed.");
    }

    @Test
    public void testGetBookByISBN() {

        String isbn = "9781449365035";

        Response response = BookStoreAPI.getBookByISBN(isbn);

        System.out.println("RESPONSE: " + response.asPrettyString());

        assertEquals(200, response.statusCode(), "Request failed.");
        assertEquals(response.jsonPath().getString("isbn"), isbn, "Request failed.");
        assertNotNull(response.jsonPath().getString("isbn"),"ISBN is null.");

    }

}
