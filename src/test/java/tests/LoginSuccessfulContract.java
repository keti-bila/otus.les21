package tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class LoginSuccessfulContract extends TestNGCitrusTestRunner {
    @Autowired
    private HttpClient restClientPost;
    private TestContext context;

    @Test(description = "Login successful")
    @CitrusTest
    public void loginSuccessfulContractTest() {
        this.context = citrus.createTestContext();

        http(
                httpActionBuilder -> httpActionBuilder
                        .client(restClientPost)
                        .send()
                        .post("login")
                        .payload("{\n" +
                                "    \"email\": \"eve.holt@reqres.in\",\n" +
                                "    \"password\": \"cityslicka\"\n" +
                                "}")
                        .accept("application/json")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClientPost)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"token\": \"QpwL5tke4Pnpja7X4\"\n" +
                        "}"));
    }
}
