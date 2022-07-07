package com.zhe.spring_movie_theater.service.impl;


import com.zhe.spring_movie_theater.model.DTO.TicketDTO;
import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.Row;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.Ticket;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.model.enums.Role;
import com.zhe.spring_movie_theater.repository.TicketRepository;
import com.zhe.spring_movie_theater.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TicketServiceImplTest {

    @Autowired
    private ModelMapper mapper;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private TicketServiceImpl ticketService;


    private TicketDTO ticketForm;

    @BeforeAll
    public void beforeTest() {
        Screening screening = new Screening();
        Row row = new Row();
        User user = new User();
        this.ticketForm = new TicketDTO(screening, row, 2, 12, user);
    }

    @Test
    public void testSaveScreeningSameAsDTO() {
        Ticket ticket = mapper.map(ticketForm, Ticket.class);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(new Ticket());

        assertEquals(ticketForm.getScreening(), ticket.getScreening());
    }
}
