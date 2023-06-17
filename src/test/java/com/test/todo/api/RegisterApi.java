package com.test.todo.api;

import com.test.todo.config.EndPoint;
import com.test.todo.objects.User;
import com.test.todo.utilies.ConfigUtils;
import com.test.todo.utilies.UserUtils;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterApi {
    private static List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;

    public String getFirstName() {
        return this.firstName;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return this.userId;
    }

    public List<Cookie> getRestAssuredCookies() {
        return restAssuredCookies;
    }

    public void register() {
        User user = UserUtils.generateRandomUser();
        Response response =
                given()
                        .baseUri(ConfigUtils.getInstance().getBaseUrl())
                        .header("Content-Type", "application/json")
                        .body(user)
                        .log().all()
                        .when()
                        .post(EndPoint.API_REGISTER_ENDPOINT)
                        .then()
                        .log().all()
                        .extract().response();  // return response

        restAssuredCookies = response.detailedCookies().asList();
        accessToken = response.path("access_token");
        userId = response.path("userID");
        firstName = response.path("firstName");
        if (response.statusCode() != 201) {
            throw new RuntimeException("some thing went wrong with rhe request");
        }
        /*String userId = response.path("userID");
        System.out.println(accessToken);
        System.out.println(userId);
        System.out.println(response.body().prettyPrint());*/

    }
}
