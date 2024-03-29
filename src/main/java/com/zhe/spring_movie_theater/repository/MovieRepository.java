package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
}
