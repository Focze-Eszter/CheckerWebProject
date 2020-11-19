package ro.siit.airports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.airports.domain.Airport;
//import ro.siit.airports.exception.RecordNotFoundException;
import ro.siit.airports.domain.User;
import ro.siit.airports.model.Search;
import ro.siit.airports.repository.AirportRepository;
import ro.siit.airports.service.AirportService;
import ro.siit.airports.service.UserService;
import ro.siit.airports.service.impl.AirportServiceImpl;
//import ro.siit.airports.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportServiceImpl airportService;

   /* @Autowired
    private UserRepository userRepository;*/

    @GetMapping("/airports/{airportCode}")
    public String retrieveAirports(final Model model) {
        final List<Airport> airports = (List<Airport>) airportRepository.findAll();
        //final List<String> names = opt.stream().map(q -> q.getName()).collect(Collectors.toList());
        model.addAttribute("myAirports", airports);
        return "airport-page";
    }

    @GetMapping("/airportCurrentPage")
    public String viewPage(Model model) {
       return listByPage(model,1, "name", "ascending");
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
        @Param("sortedField") String sortedField,
        @Param("sortedDirection") String sortedDirection) {

        Page<Airport> page = airportService.listAll(currentPage, sortedField, sortedDirection);

        long totalAirports = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Airport> airportsList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalAirports", totalAirports);
        model.addAttribute("totalPages" , totalPages);
        model.addAttribute("ourAirports", airportsList);
        model.addAttribute("sortedField", sortedField);
        model.addAttribute("sortedDirection", sortedDirection);
        return "exp";
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

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editAirportById(Model model, @PathVariable("id") Optional<Long> id)

    {

        System.out.println("editAirportById" + id);
        if (id.isPresent()) {
            Airport airport = airportService.getAirportById(id.get());
            model.addAttribute("airport", airport);
        } else {
            model.addAttribute("airport", new Airport());
        }


        return "add-edited-airport";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteAirportById(Model model, @PathVariable("id") Long id)

    {

        System.out.println("deleteAirportById" + id);

        airportService.deleteAirportById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createAirport", method = RequestMethod.POST)
    public String createOrUpdateAirport(Airport airport)
    {
        System.out.println("createOrUpdateAirport ");

        airportService.createOrUpdateAirport(airport);

        return "redirect:/";
    }
}





