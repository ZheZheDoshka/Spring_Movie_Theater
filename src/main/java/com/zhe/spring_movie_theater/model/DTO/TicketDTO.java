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

    public TicketDTO(Screening screening, Row num_row, int seat, int cost, User user) {
        this.screening = screening;
        this.num_row = num_row;
        this.seat = seat;
        this.cost = cost;
        this.user = user;
    }
}
