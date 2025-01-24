package tests.bookstore;

import api.bookstore.BookStoreAPI;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBookStoreTests extends BaseTest {

    @Test
    public void testGetAllBooks() {
        Response response = BookStoreAPI.getAllBooks();
        System.out.println("RESPONSE: " + response.asPrettyString());
        assertEquals(200, response.statusCode(), "Request failed.");
    }

}
