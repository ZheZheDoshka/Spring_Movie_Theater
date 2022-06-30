package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.model.enums.Role;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserValidatorTest {

    private UserDTO invalid_username;
    private UserDTO invalid_password;
    private UserDTO valid_user;
    private UserDTO repeated_username;

    @Autowired
    private UserValidator userValidator;

    @BeforeAll
    public void beforeTest() {
        this.invalid_username = new UserDTO ("u", "n111111111", Role.USER);
        this.valid_user = new UserDTO ("userTest1", "goodpassword", Role.USER);
        this.repeated_username = new UserDTO ( "admin1610", "123qweqwe", Role.USER);
        this.invalid_password = new UserDTO ( "userTest33", "1", Role.USER);
    }

    @DisplayName("Valid User Test")
    @Test
    public void testValidUser() {
        Errors errors = new BeanPropertyBindingResult(valid_user, "");
        userValidator.validate(valid_user, errors);

        assertFalse(errors.hasErrors());
    }

    @DisplayName("Invalid Username Test")
    @Test
    public void testInvalidUsername() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_username, "");
        errors_expected.rejectValue("username","login.invalid.username");

        Errors errors = new BeanPropertyBindingResult(invalid_username, "");
        userValidator.validate(invalid_username, errors);

        assertEquals(errors_expected, errors);
    }

    @DisplayName("Invalid Password Test")
    @Test
    public void testInvalidPassword() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_password, "");
        errors_expected.rejectValue("password","login.invalid.password");

        Errors errors = new BeanPropertyBindingResult(invalid_password, "");
        userValidator.validate(invalid_password, errors);

        assertEquals(errors_expected, errors);
    }


    @DisplayName("Repeated Username Test")
    @Test
    public void testRepeatedUsername() {
        Errors errors_expected = new BeanPropertyBindingResult(repeated_username, "");
        errors_expected.rejectValue("username", "login.repeating.username");
        Errors errors = new BeanPropertyBindingResult(repeated_username, "");
        userValidator.validate(repeated_username, errors);

        assertEquals(errors_expected, errors);
    }
}
