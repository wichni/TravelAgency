package com.example.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.travel.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}