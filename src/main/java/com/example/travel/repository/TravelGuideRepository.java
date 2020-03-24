package com.example.travel.repository;

import com.example.travel.model.TravelGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TravelGuideRepository extends JpaRepository<TravelGuide, Long> {

    @Query(value = "select tg.name as guide_name, sum(r.negotiated_price) as sum_price\n" +
            "from travel_guide tg, trip tr, reservation r\n" +
            "where tr.guide_id=tg.id\n" +
            "and r.trip_id= tr.id\n" +
            "and tr.date_of_departure = :date\n" +
            "group by tg.id\n" +
            "order by sum_price DESC\n" +
            "limit 1;",nativeQuery = true)
    List<String> findWhichGuideSoldTripsForBiggestPrice(LocalDate date);
}
