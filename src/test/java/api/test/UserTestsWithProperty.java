package api.test;

import api.endpoints.UserEndPointsWithProperty;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsWithProperty {
    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setUp(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(8,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // Code for Logging
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testCreateUser(){
        logger.info("*********************************Creating User************************************************");
        Response response = UserEndPointsWithProperty.createUser(userPayload);
        response.then().log().all();
        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("*********************************Get UserBy Name************************************************");
        Response response = UserEndPointsWithProperty.getUser(this.userPayload.getUsername());
        response.then().log().all();
        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        logger.info("*********************************Update UserBy Name************************************************");
        userPayload.setUsername(faker.name().username());
        Response response = UserEndPointsWithProperty.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        response.then().assertThat().statusCode(200);

        Response responseAfterUpdate = UserEndPointsWithProperty.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        responseAfterUpdate.then().assertThat().statusCode(200);
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        logger.info("*********************************Delete UserBy Name************************************************");
        Response response = UserEndPointsWithProperty.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        response.then().assertThat().statusCode(200);
    }
}
