package com.zhe.spring_movie_theater.model.DTO;

import com.zhe.spring_movie_theater.model.entity.Hall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RowDTO {
    private Long id;
    private Hall hall;
    private int number;
    private int seat_capacity;
}
