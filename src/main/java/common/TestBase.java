package common;

import static common.BaseConfig.getRequestSpecification;
import static common.BaseConfig.getResponseSpecification;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;

public class TestBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseOkSpec;
    public static Gson gson;

    @BeforeClass
    public static void setup() throws IOException {
        BasicConfigurator.configure();
        RestAssured.baseURI = BaseURIBuilder.buildBaseEnvUrl();
        JsonPath.config = new JsonPathConfig("UTF-8");
        RestAssured.defaultParser = Parser.JSON;

        requestSpec = getRequestSpecification();
        responseOkSpec = getResponseSpecification();
        gson = new Gson();
    }


}
