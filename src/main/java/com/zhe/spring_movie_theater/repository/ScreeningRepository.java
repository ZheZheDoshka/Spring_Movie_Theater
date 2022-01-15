package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findAll();
}
