package com.example.travel.repository;

import com.example.travel.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findAllByDateOfDeparture(LocalDate date);

    @Query(value = "select\n" +
            "sum(r.negotiated_price) as sold_sum, \n" +
            "ROUND(avg(r.negotiated_price),2) as avg_price\n" +
            "from \n" +
            "trip t, destination d, reservation r\n" +
            "where \n" +
            "t.destination_id=d.id\n" +
            "and r.id = t.id and t.id= :tripId\n" +
            "Group by d.destination_name, t.id\n" +
            "order by sold_sum desc;", nativeQuery = true)
    List<String> findByAvgAndSum(@Param("tripId") Long id);
}


