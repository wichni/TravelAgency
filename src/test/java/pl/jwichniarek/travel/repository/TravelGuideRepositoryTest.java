package pl.jwichniarek.travel.repository;

import pl.jwichniarek.travel.model.TravelGuide;
import pl.jwichniarek.travel.model.Trip;
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
public class TravelGuideRepositoryTest {

    private Trip trip(long id) {
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }

    private TravelGuide guide1 = new TravelGuide(1L, "Bartek Pospiech", List.of(trip(1), trip(2)));
    private TravelGuide guide2 = new TravelGuide(2L, "Jan Pospieszalski", List.of(trip(2), trip(2)));

    @Autowired
    private TravelGuideRepository travelGuideRepository;

    @Test
    public void shouldFindAllGuide() {
        //when
        List<TravelGuide> actual = travelGuideRepository.findAll();

        //then
        Assertions.assertThat(actual).containsExactlyInAnyOrder(guide1, guide2);
    }

    @Test
    public void shouldFindGuideById() {
        //when
        Optional<TravelGuide> actual = travelGuideRepository.findById(2L);

        //then
        assertThat(actual.get()).isEqualTo(guide2);
    }

    @Test
    @Transactional
    public void shouldAddNewGuideToDatabase() {
        //given
        TravelGuide newGuide = new TravelGuide(3L, "Wojtek Sucharski", null);

        //when
        travelGuideRepository.save(newGuide);

        //then
        assertThat(travelGuideRepository.findAll().contains(newGuide));
    }

    @Test
    @Transactional
    public void shouldUpdateGuide() {
        //given
        TravelGuide updateGuide = new TravelGuide(2L, "Jan Pospieszalski");

        //when
        travelGuideRepository.save(updateGuide);

        //then
        assertThat(travelGuideRepository.count()).isEqualTo(2L);
        assertThat(travelGuideRepository.findById(2L).get()).isEqualTo(updateGuide);
    }

    @Test
    @Transactional
    public void shouldDeleteGuide() {
        //when
        travelGuideRepository.deleteById(1L);

        //then
        assertThat(travelGuideRepository.findById(1L).isEmpty());
    }
}