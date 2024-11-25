package com.mock_builder;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public User getUser(String name, @Nullable Integer age, Boolean isAdult) {
        return new User(name, age, isAdult);
    }

    public User getAdult(@Nullable String name, Boolean isAdult) {
        return new User(name, isAdult);
    }
}
