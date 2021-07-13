package tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class UserNotFoundContract extends TestNGCitrusTestRunner {
    @Autowired
    private HttpClient restClient;
    private TestContext context;

    @Test(description = "User not found contract test")
    @CitrusTest
    public void userNotFoundContractTest() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users/23"));

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .status(HttpStatus.NOT_FOUND));
    }
}
