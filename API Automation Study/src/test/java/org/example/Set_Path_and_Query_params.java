package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Set_Path_and_Query_params {

    @Test
    void setPathParams(){
        RestAssured.given()
                .pathParam("path","objects/1")
                .queryParam("","")
                //no need to add query params in url path ret assured will take automatically
                .when().get("https://api.restful-api.dev/{path}").then().log().all();

    }

}
