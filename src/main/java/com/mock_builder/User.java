package com.mock_builder;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Nullable String name;
    @Nullable Integer age;
    Boolean isAdult;

    public User(@Nullable String name, Boolean isAdult) {
        this.name = name;
        this.isAdult = isAdult;
    }
}
