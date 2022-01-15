package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Row;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowRepository extends JpaRepository<Row, Long> {

}
