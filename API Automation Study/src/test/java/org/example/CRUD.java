package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUD {

@Test
  void getRequest(){
    ValidatableResponse response = given()
            .when()
            .get("https://api.restful-api.dev/objects")
            .then()
            .statusCode(200)
            .log().all(); // all(); returns object of ValidatableResponse

  response.body("[0].id",equalTo("1")); // we uses body to assert responce without storing into any variables
  response.body("[0].name",equalTo("Google Pixel 6 Pro"));// equalTo() this is Matchers class static method
 // "[0].id" and  "[0].name" are json paths
}

String id=null;
@Test
void postRequest(){
  //belwo is json data for post request
//  {
//    "name": "Apple MacBook Pro 16",
//          "data": {
//    "year": 2019,
//            "price": 1849.99,
//            "CPU model": "Intel Core i9",
//            "Hard disk size": "1 TB"
//  }
//  }
  Map<String, Object> dataMap = new HashMap<>(); // Map used to store data
  dataMap.put("year", 2019);
  dataMap.put("price", 1849.99);
  dataMap.put("CPU model", "Intel Core i9");
  dataMap.put("Hard disk size", "1 TB");
  Map<String, Object> requestBody = new HashMap<>();
  requestBody.put("name", "Apple MacBook Pro 16");
  requestBody.put("data", dataMap);
//  <dependency>  Need this depedancy when passing map as object otherwise can get error - java.lang.IllegalStateException: Cannot serialize object because no JSON serializer found in classpath. Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.
//          <groupId>com.fasterxml.jackson.core</groupId>
//          <artifactId>jackson-databind</artifactId>
//          <version>2.17.1</version>
//          </dependency>
  Response response = given()
          //  .contentType("application/json")
          .header("Content-Type", "application/json")
          .body(requestBody)
          .when()
          .post("https://api.restful-api.dev/objects")
          .then().statusCode(200)
          .and().log().all()// and() used to add more assertions
          .extract().response();
  id=response.jsonPath().getString("id");// used json path to extract responce and store to ID varaible

}
@Test(dependsOnMethods = {"postRequest"})
void putRequest(){
  System.out.println("Id generated from post request is = "+id);
  String jsonBody = "{\n" +
          "  \"name\": \"Apple MacBook Pro 16\",\n" +
          "  \"data\": {\n" +
          "    \"year\": 2019,\n" +
          "    \"price\": 2049.99,\n" +
          "    \"CPU model\": \"Intel Core i9\",\n" +
          "    \"Hard disk size\": \"1 TB\",\n" +
          "    \"color\": \"silver\"\n" +
          "  }\n" +
          "}";
  RestAssured
          .given()
          .contentType("application/json")
          .body(jsonBody)
          .when().put("https://api.restful-api.dev/objects/"+id)
          .then()
          .statusCode(200)
          .log().all();
}
@Test(dependsOnMethods = {"postRequest"})
void deleteRequest(){
  System.out.println("Id generated from post request is = "+id);
          Response re=RestAssured
          .when()
          .delete("https://api.restful-api.dev/objects/"+id)
                  .then().extract().response();
       String  MessageFromServer=  re.jsonPath().getString("message");// extracted message from server response
  System.out.println("Messsage from server : "+MessageFromServer);

}



}
