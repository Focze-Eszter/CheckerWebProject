package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airport;
import ro.siit.airports.model.Search;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.service.AirportService;
//import ro.siit.airports.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

   /* @Autowired
    private UserRepository userRepository;*/

    @GetMapping("/airports/{airportCode}")
    public String retrieveAirports(final Model model) {
        final List<Airport> airports = airportRepository.findAll();
        //final List<String> names = opt.stream().map(q -> q.getName()).collect(Collectors.toList());
        model.addAttribute("myAirports", airports);
        return "airport-page";
    }

    @GetMapping("/search")
    public ModelAndView displaySearchPage(Model model) {
        final ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("search", new Search());
        return modelAndView;

    }

    @PostMapping("/search")
    public ModelAndView displaySearchResults(final Search search) {
        final ModelAndView modelAndView = new ModelAndView("airport-page");
        final List<Airport> list = airportService.findFilteredAirports(search);
        modelAndView.addObject("myAirports", list);
        return modelAndView;
    }

    /*@GetMapping("/login")
    public String showLoginPage(Model model) {
        //List<User> users = userRepository.findByUser(String user);
        String users;
        /*model.getAttribute("user", users);
        return "login-page";
    } */

}