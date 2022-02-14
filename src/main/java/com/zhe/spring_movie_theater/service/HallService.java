package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HallService {
    void save(Hall hall);

    Page<Hall> findAllHalls(String sort, Pageable pageable);

    List<Hall> findAllHalls();

    Hall findById(Long id);
}
