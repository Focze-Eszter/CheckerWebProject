package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Airport;

import java.util.List;

@Repository
public interface AirportRepository extends PagingAndSortingRepository<Airport, Long> {


    List<Airport> findByCountryAndCity(String country, String city);

    List<Airport> findByCountry(String country);

    List<Airport> findByCity(String city);
}



