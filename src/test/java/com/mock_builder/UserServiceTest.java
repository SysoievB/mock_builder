package com.mock_builder;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {
    @InjectMocks
    UserService service;

    @Test
    void getFullUser() {
        val user = user().name(null).age(null).isAdult(true).build();
        log.info("--- name: {}, --- age: {}, --- is adult: {}", user.getName(), user.getAge(), user.getIsAdult());
        val result = service.getUser(null, null, true);

        assertAll(() -> {
            assertEquals(user.getName(), result.getName());
            assertEquals(user.getAge(), result.getAge());
            assertEquals(user.getIsAdult(), result.getIsAdult());
        });
    }

    @Test
    void getAdultUser() {
        val user = user().name(null).isAdult(true).build();
        log.info("--- name: {}, --- is adult: {}", user.getName(), user.getIsAdult());
        val result = service.getAdult(null, true);

        assertAll(() -> {
            assertEquals(user.getName(), result.getName());
            assertEquals(user.getIsAdult(), result.getIsAdult());
        });
    }

    @Builder(builderMethodName = "user")
    private User getUser(@Nullable String name, @Nullable Integer age, Boolean isAdult) {
        val user = mock(User.class);
        if (isNull(name)) {
            given(user.getName()).willCallRealMethod();
        }
        if (nonNull(name)) {
            given(user.getName()).willReturn(name);
        }
        if (isNull(age)) {
            given(user.getAge()).willCallRealMethod();
        }
        if (nonNull(age)) {
            given(user.getAge()).willReturn(age);
        }
        if (nonNull(isAdult)) {
            given(user.getIsAdult()).willReturn(isAdult);
        }
        return user;
    }
}