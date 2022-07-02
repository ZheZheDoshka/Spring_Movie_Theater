package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScreeningService {
    void save(Screening screening);

    Page<Screening> findAllScreenings(String sort, Pageable pageable);

    List<Screening> findAllScreenings();

    Screening findById(Long id);

    List<Integer[][]> hallSeats(List<Ticket> tickets, List<Row> rows);

    void deleteById(Long id);
}
