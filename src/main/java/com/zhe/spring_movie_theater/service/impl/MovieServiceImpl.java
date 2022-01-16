package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.repository.MovieRepository;
import com.zhe.spring_movie_theater.repository.ScreeningRepository;
import com.zhe.spring_movie_theater.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Page<Movie> findAllMovies(String sort, Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }
}
