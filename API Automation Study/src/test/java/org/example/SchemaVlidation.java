package org.example;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class SchemaVlidation {
//    for scehama valdiation se ned dependancy
//            <dependency>
//    <groupId>io.rest-assured</groupId>
//    <artifactId>json-schema-validator</artifactId>
//    <version>5.3.0</version>
//</dependency>

    @Test(enabled = false)
void validateScheme(){
        File schema = new File(
                System.getProperty("user.dir") + "/src/test/resources/org/example/schemeValidator.json"
        );
        String path = System.getProperty("user.dir") + "/org/example/schemeValidator.json";

    given()
            .when()
            .get("https://api.restful-api.dev/objects/1")
            .then()
            .statusCode(200)
            .assertThat()
            .body(matchesJsonSchemaInClasspath(path));
}
    @Test(enabled = true)
    void validate_Keys_approach1(){

//    below is response and want to verify capacity key
//        {
//            "id": "1",
//                "name": "Google Pixel 6 Pro",
//                "data": {
//            "color": "Cloudy White",
//                    "capacity": "128 GB"
//        }
//        }

        given()
                .when()
                .get("https://api.restful-api.dev/objects/1")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.capacity", notNullValue());// notNullValue() this from Matchers
    }
    @Test(enabled = true)
    void validate_Keys_approach2(){

//    below is response and want to verify capacity key
//        {
//            "id": "1",
//                "name": "Google Pixel 6 Pro",
//                "data": {
//            "color": "Cloudy White",
//                    "capacity": "128 GB"
//        }
//        }

        given()
                .when()
                .get("https://api.restful-api.dev/objects/1")
                .then()
                .statusCode(200)
                .log().all()
                .body("data", hasKey("capacity")); // hasKey is from Matchers library
    }

    @Test(enabled = true)
    void validate_Keys_approach3(){

//    below is response and want to verify capacity key
//        {
//            "id": "1",
//                "name": "Google Pixel 6 Pro",
//                "data": {
//            "color": "Cloudy White",
//                    "capacity": "128 GB"
//        }
//        }

        Response res = given().get("https://api.restful-api.dev/objects/1");

        Map<String, Object> data = res.jsonPath().getMap("data");

        System.out.println(data.containsKey("capacity"));  // true / false
    }


}
