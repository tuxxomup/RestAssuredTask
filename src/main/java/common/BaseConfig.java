package common;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.concurrent.TimeUnit;

public class BaseConfig {

    protected static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setAccept(ContentType.JSON);
        builder.log(LogDetail.HEADERS);
        builder.addFilter(new ErrorLoggingFilter());
        return builder.build();
    }

    protected static ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectContentType(ContentType.JSON);
        builder.expectStatusCode(SC_OK);
        //builder.expectResponseTime(lessThan(2L), TimeUnit.SECONDS);
        builder.log(LogDetail.BODY);
        return builder.build();
    }
}
