package com.zhe.spring_movie_theater.controller;

import com.zhe.spring_movie_theater.model.DTO.HallDTO;
import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.DTO.RowDTO;
import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.service.HallService;
import com.zhe.spring_movie_theater.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private HallService hallService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/hall_control")
    public String home2(Model model) {
        List<Hall> halls = hallService.findAllHalls();
        model.addAttribute("halls", halls);
        return "a_hall_control";
    }

    @GetMapping("/add_hall")
    public String hall_add(Model model) {
        model.addAttribute("hallForm", new HallDTO());
        return "add_hall";
    }

    @GetMapping("/{id}/hall_change")
    public String hall_edit(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        return "a_change";
    }

    @PostMapping("/add_hall")
    public String hall_add(@ModelAttribute("hallForm") HallDTO hallForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "ucaradd";
        }
        Hall hall = mapper.map(hallForm, Hall.class);
        hallService.save(hall);
        return "redirect:/ucarcontrol";
    }

    @PostMapping("/add_movie")
    public String movie_add(@ModelAttribute("movieForm") MovieDTO movieForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "ucaradd";
        }
        Movie movie = mapper.map(movieForm, Movie.class);
        movieService.save(movie);
        return "redirect:/ucarcontrol";
    }

}
