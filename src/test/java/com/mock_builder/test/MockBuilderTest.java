package com.mock_builder.test;

import com.mock_builder.MockBuilder;
import com.mock_builder.MockBuilderExtension;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.util.Objects.nonNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockBuilderExtension.class)
class MockBuilderTest {

    @Test
    void testMyService() {
        val car = car().name("BMW").age(1).engine("V6").build();
    }

    //@Builder(builderMethodName = "car")
    //@Mock
    @MockBuilder(builderMethodName = "car")//== @Builder(builderMethodName = "car")
    private Car getCar(String name, Integer age, String engine) {
        val car = mock(Car.class);
        if (nonNull(car))
            given(car.getName()).willReturn(name);
        if (nonNull(age))
            given(car.getName()).willReturn(name);
        if (nonNull(car))
            given(car.getEngine()).willReturn(engine);
        return car;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class Car {
        String name;
        Integer age;
        String engine;
    }
}
