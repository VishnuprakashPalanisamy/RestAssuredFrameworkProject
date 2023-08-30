package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class DDTests {

    Logger logger = LogManager.getLogger(this.getClass());
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testCreateUsersUsingDataProvider(String userID, String userName, String firstName, String lastName, String email, String password, String phone){
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(firstName);
        userPayload.setLastname(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        logger.info("******************************Creating Users Using Data Provider*********************************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUsersUsingDataProvider(String userName){
        User userPayload = new User();
        userPayload.setUsername(userName);
        logger.info("******************************Deleting Users Using Data Provider*********************************");
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        response.then().assertThat().statusCode(200);
    }
}
