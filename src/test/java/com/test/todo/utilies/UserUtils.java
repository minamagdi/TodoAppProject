package com.test.todo.utilies;

import com.github.javafaker.Faker;
import com.test.todo.objects.User;

public class UserUtils {
    public static User generateRandomUser() {
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();
        return new User(firstName, lastName, email, password);
    }

}
