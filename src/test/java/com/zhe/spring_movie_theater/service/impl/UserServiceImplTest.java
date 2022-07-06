package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.model.enums.Role;
import com.zhe.spring_movie_theater.repository.UserRepository;
import com.zhe.spring_movie_theater.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    private UserDTO userForm;

    @BeforeAll
    public void beforeTest() {
        this.userForm = new UserDTO("username", "password", Role.USER);
    }

    @Test
    public void testUserSameAsDTO() {
        User user = mapper.map(userForm, User.class);
        String encoded = encoder.encode(user.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn(encoded);

        userService.save(user);

        assertEquals(userForm.getUsername(), user.getUsername());
        assertEquals(encoder.encode(userForm.getPassword()), user.getPassword());
    }
}
