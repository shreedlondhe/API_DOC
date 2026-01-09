package org.example;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Assertions {
@Test
    void AssertionUsing_Responce(){
    Response response = given()
            .when()
            .get("https://api.restful-api.dev/objects")
            .then()
            .log().all()
            .extract().response();

    Assert.assertEquals(response.getStatusCode(),200);
    Assert.assertEquals(response.header("Content-Type"),"application/json");
    Assert.assertEquals(response.jsonPath().getString("[0].name"),"Google Pixel 6 Pro");
    Assert.assertEquals(response.jsonPath().getInt("[0].id"),1);
    //Or
    Assert.assertEquals(response.jsonPath().get("[0].name").toString(),"Google Pixel 6 Pro");
    }

    @Test
    void AssertionUsing_Body(){
        ValidatableResponse validatableResponse = given()
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .log().all();
        validatableResponse.body("[0].name",equalTo("Google Pixel 6 Pro"));
        validatableResponse.body("[0].id",equalTo("1"));
        validatableResponse.header("Content-Type","application/json");


    }

}




