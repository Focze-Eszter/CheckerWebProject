package ro.siit.airports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.siit.airports.domain.Flight;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightNo(String flightNo);
    /*toate zborurile care pleaca de la un anumit aeroport la o anumita data
      query JPQL - Java Pesistence Api Query Language*/

    @Query("select f from Flight f inner join  f.departureAirport a " +
            "where f.departure >= :departureDate " +
            "and a.city = :departureCity")
    List<Flight> findFlightsByCustomRules(@Param("departureDate")LocalDateTime departureDate,
                                          @Param("departureCity") String departureCity);
}
