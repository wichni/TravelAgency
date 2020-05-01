package pl.jwichniarek.travel.repository;

import pl.jwichniarek.travel.model.Destination;
import pl.jwichniarek.travel.model.TravelGuide;
import pl.jwichniarek.travel.model.Trip;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TripRepositoryTest {

    private TravelGuide guide(long id) {
        TravelGuide guide = new TravelGuide();
        guide.setId(id);
        return guide;
    }

    private Destination destination(long id) {
        Destination destination = new Destination();
        destination.setId(id);
        return destination;
    }

    private Trip trip1 = new Trip(1L, destination(1), LocalDate.parse("2018-04-30"), LocalDate.parse("2018-05-10"), new BigDecimal("250.50"), guide(1));
    private Trip trip2 = new Trip(2L, destination(2), LocalDate.parse("2017-02-10"), LocalDate.parse("2017-02-20"), new BigDecimal("870.99"), guide(2));
    private Trip trip3 = new Trip(3L, destination(3), LocalDate.parse("2020-01-30"), LocalDate.parse("2020-02-03"), new BigDecimal("320.00"), guide(2));
    private Trip trip4 = new Trip(4L, destination(4), LocalDate.parse("2019-09-19"), LocalDate.parse("2019-09-29"), new BigDecimal("250.50"), guide(1));

    @Autowired
    private TripRepository tripRepository;

    @Test
    public void shouldFindAllTrips() {
        //when
        List<Trip> actual = tripRepository.findAll();

        //then
        Assertions.assertThat(actual).containsExactlyInAnyOrder(trip1, trip2, trip3, trip4);
    }

    @Test
    public void shouldFindTripById() {
        //when
        Optional<Trip> actual = tripRepository.findById(3L);

        //then
        assertThat(actual.get()).isEqualTo(trip3);
    }

    @Test
    public void shouldFindTripByDateOfDeparture() {
        //when
        Optional<Trip> actual = tripRepository.findAllByDateOfDeparture(LocalDate.parse("2019-09-19"));

        //then
        assertThat(actual.get()).isEqualTo(trip4);
    }

    @Test
    @Transactional
    public void shouldAddNewTripToDatabase() {
        //given
        Trip newTrip = new Trip(5L, null, LocalDate.parse("2020-02-09"), LocalDate.parse("2020-02-19"), new BigDecimal("550.00"), null);

        //when
        tripRepository.save(newTrip);

        //then
        assertThat(tripRepository.findAll()).contains(newTrip);
    }

    @Test
    @Transactional
    public void shouldUpdateTrip() {
        //given
        Trip updateGuide = new Trip(4L, destination(4), LocalDate.parse("2020-09-19"), LocalDate.parse("2020-09-29"), new BigDecimal("350.50"), guide(2));

        //when
        tripRepository.save(updateGuide);

        //then
        assertThat(tripRepository.count()).isEqualTo(4L);
        assertThat(tripRepository.findById(4L).get()).isEqualTo(updateGuide);
    }

    @Test
    @Transactional
    public void shouldDeleteTrip() {
        //when
        tripRepository.deleteById(2L);

        //then
        assertThat(tripRepository.findById(2L).isEmpty());
    }
}
