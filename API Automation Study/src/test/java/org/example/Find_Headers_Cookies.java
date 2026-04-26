package org.example;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Find_Headers_Cookies {

    
@Test
void toGetAllCookies(){
    Response re = given()
            .when()
            .get("https://api.restful-api.dev/objects")
            .then()
            .log().all().extract().response();
    Map<String, String> cookies = re.getCookies();
               System.out.println(cookies);
}

    @Test
    void toGetSingleCookies(){
        Response re = given()
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .log().all().extract().response();
            String cookieValue=re.getCookie("<Cookies kay>");
        System.out.println(cookieValue);

    }
@Test
    void toGetAllHeaders(){
        Response re = given()
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .log().all().extract().response();
        Headers headers = re.getHeaders();
    }
    @Test
    void toGetSingleHeader(){
        Response re = given()
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .log().all().extract().response();
        Headers headers = re.getHeaders();

        for (Header header : headers) {
            System.out.println("Key: " + header.getName() +
                    " Value: " + header.getValue());
        }
    }

}
