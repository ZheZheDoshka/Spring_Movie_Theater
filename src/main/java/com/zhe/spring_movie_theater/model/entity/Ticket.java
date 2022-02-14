package com.zhe.spring_movie_theater.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*; //wow, nice feature!
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening", referencedColumnName = "id")
    private Screening screening;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "num_row", referencedColumnName = "id")
    private Row num_row;

    @Column(name = "seat")
    private int seat;

    @Column(name = "cost")
    private int cost;

    public Ticket(Screening screening, Row num_row, int seat, int cost) {
        this.screening = screening;
        this.num_row = num_row;
        this.seat = seat;
        this.cost = cost;
    }
}
