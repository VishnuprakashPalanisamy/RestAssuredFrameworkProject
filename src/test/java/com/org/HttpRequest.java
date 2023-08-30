package com.org;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;


public class HttpRequest {

    @Test
    public void testGetAllUsersRequest() {
        RestAssured.baseURI = "https://reqres.in/api/users";

        Response response = RestAssured.given()
                            .contentType("application/json")
                            .accept("application/json")
                            .when()
                            .get();

        response.then().statusCode(200).log().all();

        response.then().body("total_pages", equalTo(2));

    }

    @Test
    public void testGetUserRequest() {
        RestAssured.baseURI = "https://reqres.in/api/users/4";

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get();

        response.then().statusCode(200).log().all();

        response.then().body("data.first_name", equalTo("Eve"));

    }


    @Test
    public void testPostCreateUserRequest() {
        RestAssured.baseURI = "https://reqres.in/api/users";

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "VishnuprakashPalanisamy");
        hashMap.put("job", "Cloudera");

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(hashMap)
                .when()
                .post();

        response.then().statusCode(201).log().all();

        response.then().body("name", equalTo("VishnuprakashPalanisamy"));

        int id = response.jsonPath().getInt("id");
        System.out.println("Id is : "+id);

        String createdAt = response.jsonPath().get("createdAt");
        System.out.println("createdAt : " +createdAt);
    }

    @Test
    public void testPutUpdateUserRequest() {
        RestAssured.baseURI = "https://reqres.in/api/users/694";

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Vishnuprakash");
        hashMap.put("job", "Senior SDET");

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(hashMap)
                .when()
                .put();

        response.then().statusCode(200).log().all();

        response.then().body("job", equalTo("Senior SDET"));

    }

    @Test
    public void testDeleteUserRequest() {
        RestAssured.baseURI = "https://reqres.in/api/users/2";

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .delete();

        response.then().statusLine("HTTP/1.1 204 No Content").statusCode(204).log().all();

    }


}
