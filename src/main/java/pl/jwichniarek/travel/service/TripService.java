package pl.jwichniarek.travel.service;

import pl.jwichniarek.travel.exception.TripSoldIdException;
import pl.jwichniarek.travel.exception.TripsByDayException;
import pl.jwichniarek.travel.model.Trip;
import pl.jwichniarek.travel.repository.TripRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addNewTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Trip updateTrip(Trip trip) {
        tripRepository.deleteById(trip.getId());
        return tripRepository.save(trip);
    }

    public ResponseEntity<Void> deleteTripById(Long id) {
         tripRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Trip> getTripsByDayOfDeparture(LocalDate day) {
        Optional<Trip> any = tripRepository.findAllByDateOfDeparture(day).stream().findAny();
        if (any.isPresent())
        return any.map(trip -> new ResponseEntity<>(trip, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        else
            throw new TripsByDayException("Wrong day exception");
    }

    public ResponseEntity<String> findAvgAndSumPriceForWhichTripSold(Long id) {
        Optional<String> any = tripRepository.findByAvgAndSum(id).stream().findAny();
        if (any.isPresent()) {
            return any.map(s -> new ResponseEntity<>(s, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }else {
            throw new TripSoldIdException("Wrong id exception");
        }
    }
}