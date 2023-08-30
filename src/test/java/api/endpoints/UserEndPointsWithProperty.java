package api.endpoints;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

public class UserEndPointsWithProperty {

    // method created for getting urls from property file
    public static ResourceBundle getUrl(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("routes");
        return resourceBundle;
    }

    public static Response createUser(User payload){
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrl().getString("post_url"));
        return response;
    }

    public static Response getUser(String userName){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", userName)
                .when()
                .get(getUrl().getString("get_url"));
        return response;
    }

    public static Response updateUser(String userName, User payload){
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", userName)
                .body(payload)
                .when()
                .put(getUrl().getString("update_url"));
        return response;
    }

    public static Response deleteUser(String userName){

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("userName", userName)
                .when()
                .delete(getUrl().getString("delete_url"));
        return response;
    }

}
