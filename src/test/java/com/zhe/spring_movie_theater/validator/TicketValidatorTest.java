package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;

public class TicketValidatorTest {

    private UserDTO invalid_user;
    private UserDTO valid_user;
    private UserDTO repeated_user;

    @BeforeEach
    public void beforeTest() {
        this.invalid_user = new UserDTO ("u", "n", Role.USER);
        this.valid_user = new UserDTO ("userTest1", "goodpassword", Role.USER);
        this.repeated_user = new UserDTO ( "admin1610", "123qwe", Role.USER);
    }
}
