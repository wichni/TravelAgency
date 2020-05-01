package pl.jwichniarek.travel.service;

import pl.jwichniarek.travel.model.Reservation;
import pl.jwichniarek.travel.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addNewReservation (Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Reservation reservation) {
        reservationRepository.deleteById(reservation.getId());
        return reservationRepository.save(reservation);
    }

    public ResponseEntity<Void> deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
