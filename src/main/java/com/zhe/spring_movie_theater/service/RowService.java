package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.model.entity.Row;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RowService {

    void save(Row row);

}
