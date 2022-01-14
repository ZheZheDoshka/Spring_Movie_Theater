package com.zhe.spring_movie_theater.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ScreeningDTO {

    private Long id;
    private int hall;
    private int movie;
    private Date time;
}
