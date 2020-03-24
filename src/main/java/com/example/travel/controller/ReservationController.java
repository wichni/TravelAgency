package com.example.travel.controller;

import com.example.travel.model.Reservation;
import com.example.travel.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation/add")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addNewReservation(reservation);
    }

    @GetMapping(value = "/reservations")
    public List<Reservation> reservationListView() {
        return reservationService.findAll();
    }

    @PutMapping("/reservation/update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/reservation/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        return reservationService.deleteReservationById(id);
    }
}