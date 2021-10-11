import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    public void setup() throws IOException {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        JsonPath.config = new JsonPathConfig("UTF-8");
        RestAssured.defaultParser = Parser.JSON;

    }

}
