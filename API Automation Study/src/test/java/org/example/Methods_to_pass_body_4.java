package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Methods_to_pass_body_4 {
    //there are 4 way to pass request body to method in API Automation
    // for json object need to convert into string i.e  used toString();
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

   // 1) Using HashMap
   @Test
   void UsingHashMap(){
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
       ValidatableResponse validatableResponse = given()
               //  .contentType("application/json")
               .header("Content-Type", "application/json")
               .body(requestBody)
               .when()
               .post("https://api.restful-api.dev/objects")
               .then().statusCode(200)
               .and().log().all()// and() used to add more assertions
               .body("data.year", equalTo(2019));
       // conversion of ValidatableResponse to Responce
       String id = validatableResponse.extract().jsonPath().getString("id");// used json path to extract responce and store to ID varaible
       System.out.println("Id is :"+id);
   }

   @Test
    void usingJSONObject(){
//To use this we need org.json dependancy
       // Inner JSON object (data)
       JSONObject dataObject = new JSONObject();
       dataObject.put("year", 2019);
       dataObject.put("price", 1849.99);
       dataObject.put("CPU model", "Intel Core i9");
       dataObject.put("Hard disk size", "1 TB");

       // Outer JSON object
       JSONObject requestBody = new JSONObject();
       requestBody.put("name", "Apple MacBook Pro 16");
       requestBody.put("data", dataObject);

       ValidatableResponse validatableResponse = given()
                .contentType("application/json")
              // .header("Content-Type", "application/json")
               .body(requestBody.toString())
               .when()
               .post("https://api.restful-api.dev/objects")
               .then().statusCode(200).log().all();
               // and() used to add more assertions

   }
@Test
    void usingPOJO(){
    Data data = new Data(); // stored in main packege
    data.setYear(2019);
    data.setPrice(1849.99);
    data.setCPU_model("Intel Core i9");
    data.getHard_disk_size("1 TB");

    RequestBody body = new RequestBody(); // stored in main packege
    body.setName("Apple MacBook Pro 16");
    body.setData(data);

    RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post("https://api.restful-api.dev/objects")
            .then()
            .statusCode(200)
            .log().all();

}
@Test
void usingJsonFile() throws IOException {
    String filePath = "C:\\Users\\DELL\\Desktop\\API_DOC\\API Automation Study\\src\\main\\java\\org\\example\\data.json";
    String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
    JSONObject jsonObject = new JSONObject(jsonContent);
    RestAssured
            .given()
            .contentType("application/json")
            .body(jsonObject.toString()) // use to string here cause body needs string
            .when()
            .post("https://api.restful-api.dev/objects")
            .then()
            .statusCode(200)
            .log().all();


}

}
