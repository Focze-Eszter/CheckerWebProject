package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;
import ro.siit.airports.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights/{flightNo}")
    public ModelAndView displayFlightByNumber(@PathVariable("flightNo") final String flightNo) {
        final ModelAndView mav = new ModelAndView("flight-by-number");
        final List<Flight> flights = flightRepository.findByFlightNo(flightNo);
        final String flight = flights.stream()
                .findFirst()
                .map(f -> f.getAirline().getName() + " " + f.getAirline().getIata()
                        + "\n" + f.getDepartureAirport().getName() + f.getDepartureAirport().getCountry()
                        + "\n" + f.getArrivalAirport().getName() + " " + f.getArrivalAirport().getCity())
                .orElse("No data");
        mav.addObject("flight", flight);
        return mav;

    }

    @GetMapping("/flight/city/{city}/date/{date}")
    public ModelAndView displayFilteredFlights(@PathVariable("city") final String city,
                                       @PathVariable("date") final String date) {
        final ModelAndView mav = new ModelAndView("filtered-flights");
        final List<Flight> flights = flightRepository.findFlightsByCustomRules(LocalDateTime.parse(date), city);
        mav.addObject("flights", flights);
        return mav;
    }

    @GetMapping("/createFlight")
    public String addNewFlight(Flight flight) {
        System.out.println("createOrUpdateFlight");
        flightService.createOrUpdateFlight(flight);
        return "displayFlights";

    }

    @GetMapping("/displayFlights")
    public String viewPageFlights(Model model) {
        return listByPages(model,1, "flightNo", "ascending");
    }

    @GetMapping("flights/page/{pageNumber}")
    public String listByPages(Model model, @PathVariable("pageNumber") int currentPage,
                              @Param("sortedField") String sortedField,
                              @Param("sortedDirection") String sortedDirection) {

        Page<Flight> page = flightService.listAll(currentPage, sortedField, sortedDirection);

        long totalFlights = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Flight> flightsList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalFlights", totalFlights);
        model.addAttribute("totalPages" , totalPages);
        model.addAttribute("myFlights", flightsList);
        model.addAttribute("sortedField", sortedField);
        model.addAttribute("sortedDirection", sortedDirection);
        return "displayFlights";
    }


}

