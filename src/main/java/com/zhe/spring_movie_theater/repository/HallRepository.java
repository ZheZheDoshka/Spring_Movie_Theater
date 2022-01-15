package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {

}
