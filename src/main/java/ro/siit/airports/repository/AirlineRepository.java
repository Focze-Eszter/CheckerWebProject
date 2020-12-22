package ro.siit.airports.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airline;
import ro.siit.airports.domain.Airport;

import java.awt.print.Pageable;
import java.util.List;



    @Repository
    public interface AirlineRepository extends PagingAndSortingRepository<Airline, Long> {


        List<Airline> findByNameAndCountry(String name, String country);

        //List<Airline> findAllByNameContainingOrCountryOrIcao(String name, String country, String icao, Pageable pageable);

        List<Airline> findByCountry(String country);

        List<Airline> findByIcao(String icao);


    }

