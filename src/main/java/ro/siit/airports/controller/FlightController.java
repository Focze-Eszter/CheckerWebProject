package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

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
}
