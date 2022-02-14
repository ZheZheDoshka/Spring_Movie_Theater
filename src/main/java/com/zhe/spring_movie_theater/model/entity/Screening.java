package com.zhe.spring_movie_theater.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*; //wow, nice feature!
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="screening")
@Table(name ="screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall", referencedColumnName = "id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Column(name = "time")
    private Date time;

    @Column(name = "base_cost")
    private Long base_cost;

    @OneToMany(mappedBy = "screening")
    private List<Ticket> ticketList;
}
