package base;

import auth.TokenManager;
import config.Environment;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigUtility;

public class BaseTest {

    @BeforeEach
    public void setup() {
        Environment currentEnv = Environment.getCurrentEnvironment();
        RestAssured.baseURI = currentEnv.getBaseUrl();
        int timeouts = ConfigUtility.getTimeOuts();

        RestAssured.config = RestAssured.config().httpClient(
                RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", timeouts)
        );

        System.out.println("Environment: " + currentEnv);
        System.out.println("Base URL is: " + RestAssured.baseURI);
        System.out.println("Timeouts set to: " + timeouts + "ms");
    }

    @AfterEach
    public void tearDown() {
        TokenManager.tearDown();
        System.out.println("Tokens are cleared after test completion.");
    }
}
