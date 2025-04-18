package com.mock_builder;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserValidatorValidationWithoutMockBuilderTest {

    @InjectMocks
    private UserValidator userValidator;

    @Test
    void shouldPassValidationForValidUsers() {
        val users = List.of(
                getFullUser("Bob", "Smith", "France", 35),
                getFullUser("Jason", "Born", "Germany", 25),
                getFullUser("Rob", "Dilan", "Czech Republic", 18)
        );

        assertThatCode(() -> userValidator.validateUsers(users))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldFailIfUserAgeLoweredThan18() {
        val users = List.of(
                getUserWithAge(20),
                getUserWithAge(17),
                getUser()
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("All users must be adults age >= 18");
    }

    @Test
    void shouldFailIfNameIsNotCapitalized() {
        val users = List.of(
                getUserWithAgeAndNameAndSurname("Bob", "Smith", 35),
                getUserWithAgeAndName("jason", 25),
                getUserWithAge(18)
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User name and surname must start with an uppercase letter");
    }

    @Test
    void shouldFailIfSurnameIsNotCapitalized() {
        val users = List.of(
                getUserWithAgeAndNameAndSurname("Bob", "Smith", 35),
                getUserWithAgeAndNameAndSurname("Jason", "born", 25),
                getUserWithAge(18)
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User name and surname must start with an uppercase letter");
    }

    @Test
    void shouldFailIfUserIsNotFromEurope() {
        val users = List.of(
                getFullUser("Bob", "Smith", "France", 35),
                getFullUser("Jason", "Born", "USA", 25),
                getUserWithAgeAndNameAndSurname("Rob", "Dilan", 18)
        );

        assertThatThrownBy(() -> userValidator.validateUsers(users))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("All users must be from a European country");
    }

    private User getUser() {
        return mock(User.class);
    }

    private User getUserWithAge(int age) {
        val user = getUser();
        given(user.getAge()).willReturn(age);
        return user;
    }

    private User getUserWithAgeAndName(String name, int age) {
        val user = getUserWithAge(age);
        given(user.getName()).willReturn(name);
        return user;
    }

    private User getUserWithAgeAndNameAndSurname(String name, String surname, int age) {
        val user = getUserWithAgeAndName(name, age);
        given(user.getSurname()).willReturn(surname);
        return user;
    }

    private User getFullUser(String name, String surname, String country, int age) {
        val user = getUserWithAgeAndNameAndSurname(name, surname, age);
        given(user.getCountry()).willReturn(country);
        return user;
    }
}
