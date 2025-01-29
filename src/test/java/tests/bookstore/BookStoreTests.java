package tests.bookstore;

import api.account.AccountAPI;
import api.bookstore.BookStoreAPI;
import api.common.CommonMessages;
import api.common.ErrorMessages;
import api.common.StatusCodes;
import auth.TokenGenerator;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookStoreTests extends BaseTest {

    private String userName;
    private String password;
    private String userToken;
    private Response response;
    private String userUUID;
    private String BOOK_ONE_ISBN;
    private String BOOK_TWO_ISBN;
    private String UPDATE_ISBN;

    @BeforeEach
    public void prepare() {
        /* Create random username and password for test cases */
         userName = DataGenerator.generateRandomUserName();
         password = DataGenerator.generateRandomPassword();

         /* Get request to capture 2 ISBN numbers */
        Response getAllBooksResponse = BookStoreAPI.getAllBooks();

        BOOK_ONE_ISBN =  getAllBooksResponse.jsonPath().getString("books[0].isbn");
        BOOK_TWO_ISBN =  getAllBooksResponse.jsonPath().getString("books[1].isbn");
        UPDATE_ISBN = getAllBooksResponse.jsonPath().getString("books[2].isbn");

         /* Create user */
         response = AccountAPI.createUser(userName, password);
        /* Validate that the user is created */
        assertEquals(StatusCodes.TWO_ZERO_ONE, response.statusCode(), ErrorMessages.CREATE_USER_REQUEST_FAILED);

         userUUID = response.jsonPath().getString("userID");
        /* Generate user token */
         userToken = TokenGenerator.getToken(userName, password);
    }

    @Test
    public void createUserTest() {
        assertEquals(StatusCodes.TWO_ZERO_ZERO, response.statusCode(), ErrorMessages.REQUEST_FAILED);
    }

    @Test
    public void updateUserTest() {

        /* Assign a book to a user by ISBN and validate the request */
        Response assignBookToUser = BookStoreAPI.assignBookToUser(userUUID, BOOK_ONE_ISBN, userToken);
        assertEquals(StatusCodes.TWO_ZERO_ONE, assignBookToUser.statusCode(), ErrorMessages.ASSIGN_BOOK_TO_USER_FAILED);

        /* Update the book that is being assigned to the user by passing new book ISBN and validate the request */
        Response updateUser = AccountAPI.updateUser(userUUID, BOOK_ONE_ISBN, UPDATE_ISBN , userToken);
        assertEquals(StatusCodes.TWO_ZERO_ONE, updateUser.statusCode(), ErrorMessages.CREATE_USER_REQUEST_FAILED);
    }

    @Test
    public void deleteBookFromUser() {

        /* Assign a book to a user by ISBN and validate the request */
        Response assignBookToUser = BookStoreAPI.assignBookToUser(userUUID, BOOK_ONE_ISBN, userToken);
        assertEquals(StatusCodes.TWO_ZERO_ONE, assignBookToUser.statusCode(), ErrorMessages.ASSIGN_BOOK_TO_USER_FAILED);

        /* Assign a book to a user by ISBN and validate the request */
        Response assignSecondBookToUser = BookStoreAPI.assignBookToUser(userUUID, BOOK_TWO_ISBN, userToken);
        assertEquals(StatusCodes.TWO_ZERO_ONE, assignSecondBookToUser.statusCode(), ErrorMessages.ASSIGN_BOOK_TO_USER_FAILED);

        Response deleteBookFromUser = BookStoreAPI.deleteBookFromUser(userUUID, BOOK_TWO_ISBN, userToken);
        assertEquals(StatusCodes.TWO_ZERO_FOUR, deleteBookFromUser.statusCode(), ErrorMessages.DELETE_BOOK_FROM_USER_FAILED);

    }

    @AfterEach
    public void tearDown() {
        Response deleteUser = AccountAPI.deleteUser(userUUID, userToken);
        System.out.println(CommonMessages.USER_DELETED);
        System.out.println(CommonMessages.STATUS_CODE + deleteUser.statusCode());
    }

}
