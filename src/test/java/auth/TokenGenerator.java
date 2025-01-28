package auth;

import api.common.EndPoints;
import config.ConfigLoader;
import config.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGenerator {

    private static String generateToken(String username, String password){
        Environment currentEnv = Environment.getCurrentEnvironment();
        RestAssured.baseURI = currentEnv.getBaseUrl();

        Response response = given()
                .contentType("application/json")
                .body("{ \"userName\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .post(EndPoints.ACCOUNT_GENERATE_TOKEN);

        if ( response.statusCode() == 200 ) {
            return response.jsonPath().getString("token");
        } else {
            throw new RuntimeException("Filed to generate token for user." + username + " status code: " + response.statusCode());
        }
    }

    public static String getSupervisorToken() {
        String username = ConfigLoader.getProperty("supervisor.username");
        String password = ConfigLoader.getProperty("supervisor.password");
        return generateToken(username, password);
    }

    public static String getGeneralUserToken() {
        String username = ConfigLoader.getProperty("generalUser.username");
        String password = ConfigLoader.getProperty("generalUser.password");
        return generateToken(username, password);
    }

    public static String getToken(String username, String password){
        return generateToken(username, password);
    }

}
