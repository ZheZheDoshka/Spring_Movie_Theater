package com.zhe.spring_movie_theater.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.zhe.spring_movie_theater.model.DTO.TicketDTO;
import com.zhe.spring_movie_theater.model.DTO.UserDTO;
import com.zhe.spring_movie_theater.model.entity.*;
import com.zhe.spring_movie_theater.repository.*;
import com.zhe.spring_movie_theater.service.*;
import com.zhe.spring_movie_theater.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private HallService hallService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ModelMapper mapper;

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    List<Screening> empty = new ArrayList<>();

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
        List<Movie> movies = movieService.findAllValidMovies();
        model.addAttribute("movies", movies);
        return "home";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {
       userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User user = mapper.map(userForm, User.class);
        logger.info("New user registered:" + userForm.getUsername());
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
    public String logout(Authentication authentication, Model model) {

        logger.info("User " + authentication.getName() + "logged out "); //fix?
        return "redirect:/login";
    }

    @GetMapping("/{id}/hall_view")
    public String movie(Model model, @PathVariable String id) {
        Hall hall = hallService.findById(Long.valueOf(1));
        List<Ticket> tickets = screeningService.findById(Long.valueOf(id)).getTicketList();
        List<List<Integer>> seats = new ArrayList<>();
        List<Row> hall_rows = hall.getRowList();
        for (Row i:hall_rows) {
            List<Integer> row_seats = new ArrayList<>();
            for (int j = 0; j<i.getSeat_capacity()-1; j++) {
                row_seats.add(0);
            }
            seats.add(row_seats);
        }
        for (Ticket i:tickets) {
            seats.get(i.getNum_row().getNumber()).add(i.getSeat(),1);
        }
        model.addAttribute("seats",seats);

        return "a_hall_control";
    }

    @PostMapping("/ticket/{id}/{seat}/{row}")
    public String ticket(Model model,@PathVariable String id, @PathVariable String row, @PathVariable String seat) {
        return "buy_ticket";
    }

    @PostMapping("/buy/{id}/{seat}/{row}")
    public String buy_ticket(Authentication authentication, Model model, @PathVariable Long id,
                             @PathVariable Long row, @PathVariable Long seat) {
        Screening screening = screeningService.findById(id);
        Ticket ticket = new Ticket(screening, rowRepository.findById(id).get(), seat.intValue(), screening.getBase_cost().intValue(), userService.findByUsername(authentication.getName()));
        ticketRepository.save(ticket);
        return "redirect:/home";
    }

    @GetMapping("/{id}/screening")
    public String screenings(Model model, @PathVariable Long id) {
        List<Screening> screenings = movieService.findById(id).getScreeningList();
        model.addAttribute("screenings", screenings);
        model.addAttribute("id", id);
        return "screening";
    }

    @GetMapping("/usercabinet")
    public String usercabinet(Model model, Authentication authentication) {
        List<Ticket> tickets = userService.findByUsername(authentication.getName()).getTicketList();
        model.addAttribute("tickets", tickets);
        return "user_cabinet";
    }

    @PostMapping("/ticket/{id}/{locale}/pdf")
    public ResponseEntity<?> ticket_pdf(Model model, @PathVariable Long id,
                                        @PathVariable String locale) {

        Ticket ticket = ticketService.findById(id);
        model.addAttribute("ticket", ticket);
        Context context = new Context();
        context.setVariable("ticket", ticket);
        context.setVariable("lang", locale);
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("pdf_templates/");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        String ticketPDF = templateEngine.process("ticket_pdf_generation", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8090");

        HtmlConverter.convertToPdf(ticketPDF, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
        //return "ticket_pdf_generation";
    }
}
