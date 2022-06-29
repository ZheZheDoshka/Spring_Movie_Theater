package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.enums.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;


public class MovieValidatorTest {


    private MovieDTO invalid_movie;
    private MovieDTO valid_movie;
    //private MovieDTO repeated_movie;

    @BeforeAll
    public void beforeTest() {
        this.invalid_movie = new MovieDTO("Ф", "Ф", "", "", "", "а");
        this.valid_movie = new MovieDTO ("MovieTest1", "тест1", "тест1", "пройде", "пройде","пройде");
        //this.repeated_movie = new MovieDTO ( "admin1610", "123qwe", Role.Movie);
    }

}
