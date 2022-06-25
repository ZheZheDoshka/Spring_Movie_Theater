package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.Ticket;
import com.zhe.spring_movie_theater.repository.TicketRepository;
import com.zhe.spring_movie_theater.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findTicketsByScreening(Screening scr) {
        return ticketRepository.findByScreening(scr);
    }

    @Override
    public void generate_PDF_en(Ticket ticket) {

    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).get();
    }
}
