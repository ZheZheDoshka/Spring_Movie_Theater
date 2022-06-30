package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.enums.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MovieValidatorTest {


    private MovieDTO valid_movie;
    private MovieDTO invalid_movie_name_en;
    private MovieDTO invalid_movie_name_ua;
    private MovieDTO invalid_movie_name_ru;
    private MovieDTO invalid_movie_desc_en;
    //private MovieDTO invalid_movie_desc_ua;
    //private MovieDTO invalid_movie_desc_ru;

    @Autowired
    MovieValidator movieValidator;

    @BeforeAll
    public void beforeTest() {
        this.invalid_movie_name_en = new MovieDTO("тест1", "тест1", "M", "desc", "desc", "desc");
        this.invalid_movie_name_ua = new MovieDTO("тест1", "т", "MovieTest1", "desc", "desc", "desc");
        this.invalid_movie_name_ru = new MovieDTO("т", "тест1", "MovieTest1", "desc", "desc", "desc");
        this.invalid_movie_desc_en = new MovieDTO("тест1", "MovieTest1", "тест1", "пройде", "пройде", "");
        //this.invalid_movie_desc_ua = new MovieDTO("тест1", "MovieTest1", "тест1", "пройде", "", "desc");
        //this.invalid_movie_desc_ru = new MovieDTO("тест1", "MovieTest1", "тест1", "", "пройде", "desc");
        this.valid_movie = new MovieDTO ("тест1", "тест1", "MovieTest1", "пройде", "пройде","пройде");
    }

    @DisplayName("Valid Movie Test")
    @Test
    public void testValidMovie() {
        Errors errors = new BeanPropertyBindingResult(valid_movie, "");
        movieValidator.validate(valid_movie, errors);

        assertFalse(errors.hasErrors());
    }

    @DisplayName("Invalid Movie name (en)")
    @Test
    public void testInvalidMovieNameEn() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_movie_name_en, "");
        errors_expected.rejectValue("name_en","movie.invalid.moviename");
        Errors errors = new BeanPropertyBindingResult(invalid_movie_name_en, "");
        movieValidator.validate(invalid_movie_name_en, errors);

        assertEquals(errors_expected, errors);
    }

    @DisplayName("Invalid Movie name (ua)")
    @Test
    public void testInvalidMovieNameUa() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_movie_name_ua, "");
        errors_expected.rejectValue("name_ua","movie.invalid.moviename");
        Errors errors = new BeanPropertyBindingResult(invalid_movie_name_ua, "");

        assertEquals(errors_expected, errors);
    }

    @DisplayName("Invalid Movie name (ru)")
    @Test
    public void testInvalidMovieNameRu() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_movie_name_ru, "");
        errors_expected.rejectValue("name_ru","movie.invalid.moviename");
        Errors errors = new BeanPropertyBindingResult(invalid_movie_name_ru, "");

        assertEquals(errors_expected, errors);
    }

    @DisplayName("Invalid Movie description (en)")
    @Test
    public void testInvalidMovieDescEn() {
        Errors errors_expected = new BeanPropertyBindingResult(invalid_movie_desc_en, "");
        errors_expected.rejectValue("name_ru","movie.invalid.moviedesc");
        Errors errors = new BeanPropertyBindingResult(invalid_movie_desc_en, "");

        assertEquals(errors_expected, errors);
    }
}
