package com.example.travel.repository;

import com.example.travel.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findByDestinationName(String name);

    @Query(value = "select \n" +
            "d.destination_name, c.id\n" +
            "from destination d, trip t, client c\n" +
            "where t.date_of_departure = :date\n" +
            "Group by d.destination_name, t.date_of_departure, c.id\n" +
            "Order by t.date_of_departure DESC\n" +
            "Limit 1;",nativeQuery = true)
    List<String> findDestinationByMostClientInGivenYears(LocalDate date);

}
