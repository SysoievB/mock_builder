package com.mock_builder;

import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    public void validateUsers(List<User> users) {
        usersShouldBeAdults(users);
        namesAndSurnamesShouldStartWithUpperCase(users);
        usersShouldBeFromEurope(users);
    }

    private void usersShouldBeAdults(List<User> users) {
        val allValid = users
                .stream()
                .allMatch(u -> u.getAge() >= 18);
        if (!allValid) {
            throw new RuntimeException("All users must be adults age >= 18");
        }
    }

    private void namesAndSurnamesShouldStartWithUpperCase(List<User> users) {
        val allValid = users
                .stream()
                .allMatch(u ->
                        Character.isUpperCase(u.getName().charAt(0)) &&
                                Character.isUpperCase(u.getSurname().charAt(0))
                );
        if (!allValid) {
            throw new RuntimeException("User name and surname must start with an uppercase letter");
        }
    }

    private void usersShouldBeFromEurope(List<User> users) {
        val europeanCountries = List.of(
                "France", "Germany", "Italy", "Spain", "Poland", "Netherlands", "Belgium",
                "Sweden", "Austria", "Norway", "Denmark", "Greece", "Portugal", "Finland",
                "Switzerland", "Czech Republic", "Ireland", "Hungary", "Slovakia"
        );

        val allValid = users
                .stream()
                .allMatch(u -> europeanCountries.contains(u.getCountry()));

        if (!allValid) {
            throw new RuntimeException("All users must be from a European country");
        }
    }
}
