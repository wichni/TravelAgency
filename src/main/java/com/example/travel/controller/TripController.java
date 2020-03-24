package com.example.travel.controller;

import com.example.travel.model.Trip;
import com.example.travel.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/trip/add")
    public Trip addNewTrip(@RequestBody Trip trip) {
        return tripService.addNewTrip(trip);
    }

    @GetMapping("/trips")
    public List<Trip> tripListView() {
        return tripService.findAll();
    }

    @PutMapping("/trip/update/{id}")
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    @DeleteMapping("/trip/delete/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        return tripService.deleteTripById(id);
    }

    @GetMapping("/trip/departureDate/{date}")
    public ResponseEntity<?> getTripsByDepartureDate(@PathVariable String date) {
        return tripService.getTripsByDayOfDeparture(LocalDate.parse(date));

    }

    @GetMapping("/trip/avgAndSum/{id}")
    public ResponseEntity<String> tripsAvgAndSum(@PathVariable String id) {
        return tripService.findAvgAndSumPriceForWhichTripSold(Long.parseLong(id));
    }
}