package tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class GetResourceContract extends TestNGCitrusTestRunner {
    @Autowired
    private HttpClient restClient;
    private TestContext context;

    @Test(description = "Get single resource")
    @CitrusTest
    public void getResourceContractTest() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("unknown/2"));

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"data\": {\n" +
                        "        \"id\": 2,\n" +
                        "        \"name\": \"fuchsia rose\",\n" +
                        "        \"year\": 2001,\n" +
                        "        \"color\": \"#C74375\",\n" +
                        "        \"pantone_value\": \"17-2031\"\n" +
                        "    },\n" +
                        "    \"support\": {\n" +
                        "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                        "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "    }\n" +
                        "}"));
    }
}
