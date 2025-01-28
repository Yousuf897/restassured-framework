package api.account;


import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AccountAPI {

    public static Response getUserByUUIDWithoutToken(String uuid) {
        return RestAssured
                .given()
                .header("accept", "application/json")
                .pathParam("UUID", uuid)
                .get(EndPoints.GET_USER_BY_UUID);
    }


    public static Response getUserByUUIDWithToken(String uuid, String token) {
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", uuid)
                .get(EndPoints.GET_USER_BY_UUID);
    }

    public static Response createUser(String username, String password) {
        String requestBody = "{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.CREATE_USER);
    }

    public static Response updateUser(String uuid, String isbn, String token) {
        String requestBody = "{ \"userId\": \"" + uuid + "\", \"isbn\": \"" + isbn + "\" }";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("ISBN", isbn)
                .put(EndPoints.UPDATE_BOOK_BY_ISBN);
    }

    public static Response deleteUser(String uuid, String token) {
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", uuid)
                .delete(EndPoints.DELETE_USER_BY_UUID);
    }

}
