package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.model.enums.Role;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserValidatorTest {

    private UserDTO invalid_user;
    private UserDTO valid_user;
    private UserDTO repeated_user;

    @Autowired
    private UserValidator userValidator;

    @BeforeAll
    public void beforeTest() {
        this.invalid_user = new UserDTO ("u", "n", Role.USER);
        this.valid_user = new UserDTO ("userTest1", "goodpassword", Role.USER);
        this.repeated_user = new UserDTO ( "admin1610", "123qwe", Role.USER);
    }

    @DisplayName("Valid User Test")
    @Test
    public void testValidUser() {
        Errors errors = new BeanPropertyBindingResult(valid_user, "");
        userValidator.validate(valid_user, errors);

        assertFalse(errors.hasErrors());
    }

}
