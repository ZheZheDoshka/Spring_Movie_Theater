package com.zhe.spring_movie_theater.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TicketDTO {
    private Long id;
    private int screening;
    private int num_row;
    private int seat;
    private int cost;
}
