package com.zhe.spring_movie_theater.repository;

import com.zhe.spring_movie_theater.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
