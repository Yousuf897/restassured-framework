package tests.account;

import api.account.AccountAPI;
import api.bookstore.BookStoreAPI;
import auth.TokenGenerator;
import auth.TokenManager;
import base.BaseTest;
import config.ConfigLoader;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.DataGenerator;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTests extends BaseTest {

    @Test
    public void getUnauthorizedUserByUUID() {
        // Arrange
        String userId = "befce4ac-a8fb-4c41-8da4-c9276cd4ad1e";

        // Act
       Response response = AccountAPI.getUserByUUIDWithoutToken(userId);

       // Assert
       assertEquals(401, response.statusCode(), "Request failed.");
    }

    @Test
    public void getAuthorizedUserByUUID() {

        // Arrange
        String userId = "78c586b0-5083-4c87-9fd7-65137ec69c9d";
        String userToken = TokenManager.getGeneralUserToken();


        System.out.println("Username of the user trying to generate token: " + ConfigLoader.getProperty("generalUser.username"));
        System.out.println("Password of the user trying to generate token: " + ConfigLoader.getProperty("generalUser.password"));

        // Act
        Response response = AccountAPI.getUserByUUIDWithToken(userId, userToken);


        // Assert
        assertEquals(200, response.statusCode(), "Request failed.");

    }

    @Test
    public void createUser() {
        // Arrange
        String userName = DataGenerator.generateRandomUserName();
        String password = DataGenerator.generateRandomPassword();
        // Act
        Response response = AccountAPI.createUser(userName, password);
        // Assert
        assertEquals(201, response.statusCode(), "Request failed.");
        System.out.println( "userId: " + response.jsonPath().getString("userID") );
        System.out.println( "Username: " + response.jsonPath().getString("username") );
        System.out.println( "password: " + password );

        String userUUID = response.jsonPath().getString("userID");
        String userToken = TokenGenerator.getToken(userName, password);

        Response getUserInfo = AccountAPI.getUserByUUIDWithToken(userUUID, userToken);
        System.out.println( getUserInfo.asPrettyString() );

        Response deleteUser = AccountAPI.deleteUser(userUUID, userToken);
        System.out.println("user deleted!");
        System.out.println("Status Code: " + deleteUser.statusCode());
    }

    @Test
    public void updateUser() {
        // Arrange
        final String userName = DataGenerator.generateRandomUserName();
        final String password = DataGenerator.generateRandomPassword();
        final String ISBN = "9781449325862";
        final String UPDATE_ISBN = "9781449337711";

        // Act
        Response response = AccountAPI.createUser(userName, password);

        // Assert
        assertEquals(201, response.statusCode(), "Request failed.");
        System.out.println( "userId: " + response.jsonPath().getString("userID") );
        System.out.println( "Username: " + response.jsonPath().getString("username") );
        System.out.println( "password: " + password );

        String userUUID = response.jsonPath().getString("userID");
        String userToken = TokenGenerator.getToken(userName, password);

        Response getUserInfo = AccountAPI.getUserByUUIDWithToken(userUUID, userToken);
        System.out.println( "User Info Before Update: " + getUserInfo.asPrettyString() );

        Response postBookToUser = BookStoreAPI.assignBookToUser(userUUID, ISBN, userToken);
        System.out.println( postBookToUser.asPrettyString() );

//        Response updateUser = AccountAPI.updateUser(userUUID, UPDATE_ISBN, userToken);
//        assertEquals(201, response.statusCode(), "Request failed.");
//        System.out.println( updateUser.asPrettyString() );

        Response getUpdatedUserInfo = AccountAPI.getUserByUUIDWithToken(userUUID, userToken);
        System.out.println( getUpdatedUserInfo.asPrettyString() );

        Response deleteUser = AccountAPI.deleteUser(userUUID, userToken);
        System.out.println("user deleted!");
        System.out.println("Status Code: " + deleteUser.statusCode());

    }

}
