package com.zhe.spring_movie_theater.model.DTO;

import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.model.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScreeningDTO {

    private Long id;
    private Hall hall;
    private Movie movie;
    private String time;
    private Long base_cost;
}
