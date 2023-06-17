package com.test.todo.api;

import com.test.todo.config.EndPoint;
import com.test.todo.objects.Task;
import com.test.todo.utilies.ConfigUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TasksApi {
    public void addTask(String token) {
        Task task = new Task(false, "Learn Selenium");
        Response response =
                given()
                        .baseUri(ConfigUtils.getInstance().getBaseUrl())
                        .header("Content-Type", "application/json")
                        .body(task)
                        .auth().oauth2(token)
                        .when()
                        .post(EndPoint.API_TASK_ENDPOINT)
                        .then()
                        .log().all().extract().response();

        if (response.statusCode() != 201) {
            throw new RuntimeException("something went wrong when adding todo");
        }
    }
}
