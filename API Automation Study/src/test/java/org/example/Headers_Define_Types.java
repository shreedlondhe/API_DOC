package org.example;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;

public class Headers_Define_Types {


    @Test
    void addHeaderMethod1(){
        // using simple header
    ValidatableResponse response = given()
            .header("Content-Type","application/json")
            .when()
            .get("https://api.restful-api.dev/objects")
            .then()
            .statusCode(200)
            .log().all();

}
    @Test
    void addHeaderMethod2(){
        // using Headers class
        Header h1 = new Header("Content-Type", "application/json");
        Header h2 = new Header("x-api-key", "reqres_149dc9238d4c43c68292b18d4214e98b");
        Headers headers = new Headers(h1, h2);

        HashMap<String,String> data=new HashMap<>();
        data.put("name","shree");
        data.put("job","QA Engineer");

        given().headers(headers)
                .body(data)
                .when().post("https://reqres.in/api/users").then().log().all();
    }

    @Test
    void addHeaderMethod3(){
        // using multiple header
        Header h1 = new Header("Content-Type", "application/json");
        Header h2 = new Header("x-api-key", "reqres_149dc9238d4c43c68292b18d4214e98b");

        HashMap<String,String> data=new HashMap<>();
        data.put("name","shree");
        data.put("job","QA Engineer");

                given()
                .header(h1)
                .header(h2)
                .body(data)
                .when().post("https://reqres.in/api/users").then().log().all();
    }

    @Test
    void addHeaderMethod4(){
     // using HashMap()
        HashMap<String,String> headerData=new HashMap<>();
        headerData.put("Content-Type", "application/json");
        headerData.put("x-api-key", "reqres_149dc9238d4c43c68292b18d4214e98b");


        HashMap<String,String> data=new HashMap<>();
        data.put("name","shree");
        data.put("job","QA Engineer");

        given().headers(headerData)
                .body(data)
                .when().post("https://reqres.in/api/users").then().log().all();
    }

}
