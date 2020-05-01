package pl.jwichniarek.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwichniarek.travel.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}