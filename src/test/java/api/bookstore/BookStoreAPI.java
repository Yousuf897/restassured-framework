package api.bookstore;

import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookStoreAPI {

    public static Response getAllBooks() {
        return RestAssured
                .given()
                .get(EndPoints.GET_ALL_BOOKS);
    }

    public static Response getBookByISBN(String isbn) {
        return RestAssured
                .given()
                .header("accept", "application/json")
                .when()
                .get(EndPoints.GET_BOOK_BY_ISBN + isbn);
    }

    public static Response assignBookToUser(String uuid, String isbn, String userToken) {
        String requestBody = "{ \"userId\": \"" + uuid + "\", \"collectionOfIsbns\": [ { \"isbn\": \"" + isbn + "\" } ] }";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + userToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.POST_BOOK_TO_USER);
    }

    public static Response deleteBookFromUser(String uuid, String ISBNToDelete, String userToken) {
        String requestBody = "{ \"isbn\": \"" + ISBNToDelete + "\", \"userId\": \"" + uuid + "\" }";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + userToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .delete(EndPoints.DELETE_BOOK__FROM_USER);
    }



}
