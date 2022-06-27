package com.zhe.spring_movie_theater.controller;

import com.zhe.spring_movie_theater.model.DTO.HallDTO;
import com.zhe.spring_movie_theater.model.DTO.MovieDTO;
import com.zhe.spring_movie_theater.model.DTO.RowDTO;
import com.zhe.spring_movie_theater.model.DTO.ScreeningDTO;
import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.model.entity.Movie;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.service.HallService;
import com.zhe.spring_movie_theater.service.MovieService;
import com.zhe.spring_movie_theater.service.ScreeningService;
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

import java.sql.Date;
import java.sql.Timestamp;
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

    @Autowired
    private ScreeningService screeningService;

    @GetMapping("/movie_control")
    public String movie(Model model) {
        List<Movie> movies = movieService.findAllMovies();
        model.addAttribute("movies", movies);
        return "a_movie_control";
    }

    @GetMapping("/add_movie")
    public String movie_add(Model model) {
        model.addAttribute("movieForm", new MovieDTO());
        return "add_movie";
    }

    @PostMapping("/add_movie")
    public String movie_add(@ModelAttribute("movieForm") MovieDTO movieForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "";
        }
        Movie movie = mapper.map(movieForm, Movie.class);
        movieService.save(movie);

        return "redirect:/movie_control";
    }


    @PostMapping("/{id}/add_screening")
    public String screening_add(@ModelAttribute("screeningForm") ScreeningDTO screeningForm,
                                @PathVariable String id, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "";
        }
        Screening screening = new Screening();
        Hall hall = hallService.findById(Long.valueOf(1));
        Movie movie = movieService.findById(Long.valueOf(id));
        screening.setHall(hall);
        screening.setMovie(movie);
        screeningForm.setTime(screeningForm.getTime().substring(0,9)+' '+screeningForm.getTime().substring(11)+":00");
        Timestamp date = Timestamp.valueOf(screeningForm.getTime());
        screening.setTime(date);
        screening.setBase_cost(screeningForm.getBase_cost());
        screeningService.save(screening);
        return "redirect:/{id}/screening_control";
    }

    @GetMapping("/{id}/screening_control")
    public String screening_control(Model model, @PathVariable String id) {
        Long sId = Long.valueOf(id);
        List<Screening> screenings = movieService.findById(sId).getScreeningList();
        model.addAttribute("screenings", screenings);
        model.addAttribute("id", sId);
        return "screening";
    }

    @GetMapping("/{id}/add_screening")
    public String screening_add(Model model, @PathVariable String id) {
        model.addAttribute("screeningForm", new ScreeningDTO());
        return "add_screening";
    }

    @PostMapping("/{id}/delete")
    public String sessionDelete(@PathVariable String id){
        Long sId = Long.valueOf(id);
        screeningService.deleteById(sId);
        return "redirect:/movie_control";
    }
}
