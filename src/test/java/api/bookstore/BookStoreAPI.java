package api.bookstore;

import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookStoreAPI {

    public static Response getAllBooks() {
        return RestAssured
                .given()
                .get(EndPoints.GET_ALL_BOOKS);
    }

}
