package com.zhe.spring_movie_theater.validator;

import com.zhe.spring_movie_theater.model.DTO.TicketDTO;
import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.model.entity.Ticket;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.service.TicketService;
import com.zhe.spring_movie_theater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class TicketValidator  implements Validator {
    @Autowired
    private TicketService ticketService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TicketDTO ticket = (TicketDTO) target;
        List<Ticket> tickets = ticket.getScreening().getTicketList();
        for (Ticket i:tickets) {
            if (i.getSeat()==ticket.getSeat()) {
                if(i.getNum_row().equals(ticket.getNum_row()))
                {
                    errors.rejectValue("password","login.invalid.place");
                }
            }
            }
    }
}
