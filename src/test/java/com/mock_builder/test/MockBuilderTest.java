package com.mock_builder.test;

import com.mock_builder.MockBuilder;
import com.mock_builder.MockBuilderExtension;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockBuilderExtension.class)
class MockBuilderTest {

    @MockBuilder
    Car car;

    @Test
    void testMyService() {

    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class Car {
        String name;
        int age;
        String engine;
    }
}
