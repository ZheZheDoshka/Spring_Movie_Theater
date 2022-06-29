package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.service.MovieService;
import com.zhe.spring_movie_theater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MovieValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Movie.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieDTO movie = (MovieDTO) target;
        if((movie.getName_en().length() < 2)||(movie.getName_en().length()>32))
            errors.rejectValue("name_en","movie.invalid.moviename");

        if((movie.getName_ru().length() < 2)||(movie.getName_ru().length()>32))
            errors.rejectValue("name_ru","movie.invalid.moviename");


        if((movie.getName_ua().length() < 2))
            errors.rejectValue("name_ua","movie.invalid.moviename");


        if((movie.getDescription_ua().length() < 2))
            errors.rejectValue("description_ua","movie.invalid.moviedesc");


        if((movie.getDescription_en().length() < 2))
            errors.rejectValue("description_en","movie.invalid.moviedesc");


        if((movie.getDescription_ru().length() < 2))
            errors.rejectValue("description_ru","movie.invalid.moviedesc");

}
}
