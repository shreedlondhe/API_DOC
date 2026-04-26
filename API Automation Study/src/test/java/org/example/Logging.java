package org.example;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Logging {


    @Test
    void loggingMethods(){
        ValidatableResponse re = given()
                .when()
                .get("https://api.restful-api.dev/objects")
        .then();
        ValidatableResponse allResponse = re.log().all();
        ValidatableResponse allBody = re.log().body();
        ValidatableResponse allCookies = re.log().cookies();
        ValidatableResponse allHeaders = re.log().headers();
        ValidatableResponse ifFail = re.log().ifValidationFails();
        ValidatableResponse ifAssertionFailure = re.log().ifValidationFails();
        ValidatableResponse status = re.log().status();



    }
}
