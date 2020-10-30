package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.siit.airports.model.Airport;
import ro.siit.airports.repository.AirportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/airports/{airportCode}")
    public String retrieveAirportInfo(@PathVariable("airportCode") final String code, final Model model) {
        final List<Airport> opt = airportRepository.findByIata(code);
        final List<String> names = opt.stream().map(q -> q.getName()).collect(Collectors.toList());
        model.addAttribute("names", names);
        return "my-airport";
    }
}
