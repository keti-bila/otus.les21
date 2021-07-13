package tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class RegisterSuccessfulContract extends TestNGCitrusTestRunner {
    @Autowired
    private HttpClient restClientPost;
    private TestContext context;

    @Test(description = "Register successful")
    @CitrusTest
    public void registerSuccessfulContractTest() {
        this.context = citrus.createTestContext();

        http(
                httpActionBuilder -> httpActionBuilder
                        .client(restClientPost)
                        .send()
                        .post("register")
                        .payload("{\n" +
                                "    \"email\": \"eve.holt@reqres.in\",\n" +
                                "    \"password\": \"pistol\"\n" +
                                "}")
                        .accept("application/json")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClientPost)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"id\": 4,\n" +
                        "    \"token\": \"QpwL5tke4Pnpja7X4\"\n" +
                        "}"));
    }
}
