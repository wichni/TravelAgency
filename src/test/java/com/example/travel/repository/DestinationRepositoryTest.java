package com.example.travel.repository;

import com.example.travel.model.Destination;
import com.example.travel.model.Trip;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DestinationRepositoryTest {

    private Trip trip(long id) {
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }

    private final Destination destination1 = new Destination(1L, "Kreta", trip(1));
    private final Destination destination2 = new Destination(2L, "Tajlandia", trip(2));
    private final Destination destination3 = new Destination(3L, "Tajlandia", trip(3));
    private final Destination destination4 = new Destination(4L, "Kreta", trip(4));

    @Autowired
    private DestinationRepository destinationRepository;

    @Test
    public void findAllDestination() {
        //when
        List<Destination> actual = destinationRepository.findAll();

        //then
        Assertions.assertThat(actual).containsExactlyInAnyOrder(destination1, destination2, destination3, destination4);
    }
    @Test
    public void shouldFindDestinationById() {
        //when
        Optional<Destination> actual = destinationRepository.findById(3L);

        //then
        assertThat(actual.get()).isEqualTo(destination3);
    }

    @Test
    public void shouldFindDestinationWithNameEqualToKreta() {
        //when
        List<Destination> actual = destinationRepository.findByDestinationName("Kreta");

        //then
        Assertions.assertThat(actual).containsExactlyInAnyOrder(destination1, destination4);
    }

    @Test
    @Transactional
    public void shouldAddNewDestinationToDatabase() {
        //given
        Destination newDestination = new Destination(5L, "Berlin", null);

        //when
        destinationRepository.save(newDestination);

        //then
        assertThat(destinationRepository.findAll().contains(newDestination));

    }

    @Test
    @Transactional
    public void shouldUpdateClient() {
        //given
        Destination updateDestination = new Destination(4L, "Ateny", null);

        //when
        destinationRepository.save(updateDestination);

        //then
        assertThat(destinationRepository.count()).isEqualTo(4L);
        assertThat(destinationRepository.findById(4L).get()).isEqualTo(updateDestination);

    }

    @Test
    @Transactional
    public void shouldDeleteDestination() {

        //when
        destinationRepository.deleteById(2L);

        //then
        Assertions.assertThat(destinationRepository.findById(2L)).isEmpty();
    }
}
