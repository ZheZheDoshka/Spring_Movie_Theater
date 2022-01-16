package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {


    void save(Movie movie);

    Page<Movie> findAllMovies(String sort, Pageable pageable);

    List<Movie> findAllMovies();
}
