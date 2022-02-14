package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Ticket;
import com.zhe.spring_movie_theater.repository.TicketRepository;
import com.zhe.spring_movie_theater.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }


}
