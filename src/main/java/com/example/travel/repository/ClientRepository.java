package com.example.travel.repository;

import com.example.travel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstName(String firstName);

    @Query(value = "select c.* from client c where id not in (\n" +
            "\tselect r.client_id from reservation r\n" +
            "\tinner join trip t \n" +
            "\ton t.id = r.trip_id\n" +
            "\twhere t.date_of_departure = :date);", nativeQuery = true)
    List<Client> findByClientDidNotOrderByDestination(LocalDate date);

    @Query(value = "SELECT\n" +
            "   client_id\n" +
            "FROM\n" +
            "   (SELECT r.client_id, (t.suggested_price - r.negotiated_price) as amount from reservation r\n" +
            "inner join trip t\n" +
            "on t.id = r.trip_id) as discounts\n" +
            "order by amount desc\n" +
            "limit 1;", nativeQuery = true)
    Optional<String> findByClientAndSuggestedPAndNegotiatedPrice();

}