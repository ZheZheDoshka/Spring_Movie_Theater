package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.Ticket;

import java.util.List;

public interface TicketService {

    void save(Ticket ticket);

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsByScreening(Screening scr);

    Ticket findById(Long id);

    void generate_PDF_en(Ticket ticket);
}
