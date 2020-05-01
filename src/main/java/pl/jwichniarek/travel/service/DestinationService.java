package pl.jwichniarek.travel.service;

import pl.jwichniarek.travel.model.Destination;
import pl.jwichniarek.travel.repository.DestinationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination addNewDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    public Destination updateDestination(Destination destination) {
        destinationRepository.deleteById(destination.getId());
        return destinationRepository.save(destination);
    }

    public ResponseEntity<Void> deleteById(Long id) {
        destinationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<String> findDestinationBuyByMostClient(LocalDate date) {
        return destinationRepository.findDestinationByMostClientInGivenYears(date);
    }
}
