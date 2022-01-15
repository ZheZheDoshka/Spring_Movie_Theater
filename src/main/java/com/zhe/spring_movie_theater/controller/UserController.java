package com.zhe.spring_movie_theater.controller;

import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.User;
import com.zhe.spring_movie_theater.repository.ScreeningRepository;
import com.zhe.spring_movie_theater.repository.UserRepository;
import com.zhe.spring_movie_theater.service.ScreeningService;
import com.zhe.spring_movie_theater.service.SecurityService;
import com.zhe.spring_movie_theater.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ModelMapper mapper;


    /*@GetMapping("/")
    public String home(@PageableDefault(page = 0, size = 5)
                           @SortDefault.SortDefaults({
                                   @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                           })
                                   Pageable pageable, Model model){
        Page<Screening> screenings = screeningService.findAllScreenings("sort", pageable);
        model.addAttribute("screenings", screenings);
        return "home";
    }*/

    @GetMapping("/home")
    public String home2(Model model) {
        List<Screening> screenings = screeningService.findAllScreenings();
        System.out.println(screeningRepository.findAll());
        model.addAttribute("screenings", screenings);
        return "home";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
       /* userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }*/
        User user = mapper.map(userForm, User.class);
        userService.save(user);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if(error != null) {
            model.addAttribute("error","login.error");
        }

        if(logout != null) {
            model.addAttribute("message","login.out");
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login";
    }

}
