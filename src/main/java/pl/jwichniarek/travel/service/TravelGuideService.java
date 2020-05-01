package pl.jwichniarek.travel.service;

import pl.jwichniarek.travel.model.TravelGuide;
import pl.jwichniarek.travel.repository.TravelGuideRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TravelGuideService {

    private final TravelGuideRepository travelGuideRepository;

    public TravelGuideService(TravelGuideRepository travelGuideRepository) {
        this.travelGuideRepository = travelGuideRepository;
    }

    public TravelGuide addNewGuide(TravelGuide travelGuide) {
        return travelGuideRepository.save(travelGuide);
    }

    public List<TravelGuide> findAll() {
        return travelGuideRepository.findAll();
    }

    public TravelGuide updateGuide(TravelGuide travelGuide) {
        travelGuideRepository.deleteById(travelGuide.getId());
        return travelGuideRepository.save(travelGuide);
    }

    public ResponseEntity<Void> deleteGuideById(Long id) {
        travelGuideRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<String> findGuideWhoSoldTripsForBiggestPrice(LocalDate date) {
        return travelGuideRepository.findWhichGuideSoldTripsForBiggestPrice(date);
    }
}
