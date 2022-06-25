package com.zhe.spring_movie_theater.model.DTO;

import com.zhe.spring_movie_theater.model.entity.Row;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.User;
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
    private Screening screening;
    private Row num_row;
    private int seat;
    private int cost;
    private User user;
}
