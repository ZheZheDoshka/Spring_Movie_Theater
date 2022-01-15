package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
