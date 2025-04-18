package com.mock_builder;

import lombok.Builder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserValidatorValidationTest {

    @InjectMocks
    private UserValidator userValidator;

    @Test
    void shouldPassValidationForValidUsers() {
        val users = List.of(
                user().name("Bob").surname("Smith").country("France").age(35).build(),
                user().name("Jason").surname("Born").country("Germany").age(25).build(),
                user().name("Rob").surname("Dilan").country("Czech Republic").age(18).build()
        );

        assertThatCode(() -> userValidator.validateUsers(users))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldFailIfUserAgeLoweredThan18() {
        val users = List.of(
                user().age(20).build(),
                user().age(17).build(),
                user().build()
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("All users must be adults age >= 18");
    }

    @Test
    void shouldFailIfNameIsNotCapitalized() {
        val users = List.of(
                user().name("Bob").surname("Smith").age(18).build(),
                user().name("jason").age(30).build(),
                user().age(21).build()
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User name and surname must start with an uppercase letter");
    }

    @Test
    void shouldFailIfSurnameIsNotCapitalized() {
        val users = List.of(
                user().name("Bob").surname("Smith").age(18).build(),
                user().name("Jason").surname("born").age(30).build(),
                user().age(21).build()
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User name and surname must start with an uppercase letter");
    }

    @Test
    void shouldFailIfUserIsNotFromEurope() {
        val users = List.of(
                user().name("Bob").surname("Smith").age(18).country("France").build(),
                user().name("Jason").surname("Born").age(30).country("USA").build(),
                user().name("Rob").surname("Dilan").age(21).build()
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("All users must be from a European country");
    }


    @Builder(builderMethodName = "user")
    private static User getUserBuilder(
            String name,
            String surname,
            String country,
            Integer age
    ) {
        User user = mock(User.class);
        if (nonNull(name)) {
            given(user.getName()).willReturn(name);
        }
        if (nonNull(surname)) {
            given(user.getSurname()).willReturn(surname);
        }
        if (nonNull(country)) {
            given(user.getCountry()).willReturn(country);
        }
        if (nonNull(age)) {
            given(user.getAge()).willReturn(age);
        }

        return user;
    }
}
