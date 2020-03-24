package com.example.travel.repository;

import com.example.travel.model.Client;
import com.example.travel.model.Reservation;
import com.example.travel.model.Trip;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservationRepositoryTest {

    private Client client(long id) {
        Client client = new Client();
        client.setId(id);
        return client;
    }

    private Trip trip(long id) {
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }

    private final Reservation reservation1 = new Reservation(1L, trip(1), new BigDecimal("150.99"), client(1));
    private final Reservation reservation2 = new Reservation(2L, trip(2), new BigDecimal("500.00"), client(2));
    private final Reservation reservation3 = new Reservation(3L, trip(3), new BigDecimal("240.99"), client(3));
    private final Reservation reservation4 = new Reservation(4L, trip(4), new BigDecimal("200.99"), client(4));

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void shouldFindAllReservation() {
        //when
        List<Reservation> actual = reservationRepository.findAll();

        //then
        Assertions.assertThat(actual).containsExactlyInAnyOrder(reservation1, reservation2, reservation3, reservation4);
    }

    @Test
    public void shouldFindReservationById() {
        //when
        Optional<Reservation> actual = reservationRepository.findById(2L);

        //then
        assertThat(actual.get()).isEqualTo(reservation2);
    }

    @Test
    @Transactional
    public void shouldAddNewReservationToDatabase() {
        //given
        Reservation newReservation = new Reservation(6L, new BigDecimal("130.90"));

        //when
        reservationRepository.save(newReservation);

        //then
        assertThat(reservationRepository.findAll().contains(newReservation));
    }

    @Test
    @Transactional
    public void shouldUpdateReservation() {
        //given
        Reservation updateReservation = new Reservation(4L, trip(3), new BigDecimal("180.99"), client(3));

        //when
        reservationRepository.save(updateReservation);

        //then
        assertThat(reservationRepository.count()).isEqualTo(4L);
        Assertions.assertThat(reservationRepository.findById(4L).get()).isEqualTo(updateReservation);
    }

    @Test
    @Transactional
    public void shouldDeleteReservation() {
        //when
        reservationRepository.deleteById(2L);

        //then
        assertThat(reservationRepository.findById(2L).isEmpty());
    }
}
