package ro.siit.airports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Flight;
import ro.siit.airports.repository.FlightRepository;

import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Page<Flight> listAll(int pageNumber, String sortedField, String sortedDirection) {
        Sort sort = Sort.by(sortedField);
        sort = sortedDirection.equals("ascending") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return flightRepository.findAll(pageable);
    }


    public Flight createOrUpdateFlight(Flight flightEntity) {
        System.out.println("createOrUpdateAirport");

        if (flightEntity.getId() == 0) {
            flightEntity = flightRepository.save(flightEntity);

            return flightEntity;
        } else {
            // update existing entry
            Optional<Flight> flight = flightRepository.findById(flightEntity.getId());

            if (flight.isPresent()) {
                Flight newEntity = flight.get();
                newEntity.setFlightNo(flightEntity.getFlightNo());
                newEntity.setDeparture(flightEntity.getDeparture());
                newEntity.setDepartureAirport(flightEntity.getDepartureAirport());
                newEntity.setArrival(flightEntity.getArrival());
                newEntity.setArrivalAirport(flightEntity.getArrivalAirport());
                newEntity.setAirline(flightEntity.getAirline());

                newEntity = flightRepository.save(newEntity);

                return newEntity;
            } else {
                flightEntity = flightRepository.save(flightEntity);

                return flightEntity;
            }
        }

    }
}
