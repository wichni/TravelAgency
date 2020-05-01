package pl.jwichniarek.travel.controller;

import pl.jwichniarek.travel.model.Destination;
import pl.jwichniarek.travel.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/destination/add")
    public Destination addDestination(@RequestBody Destination destination) {
        return destinationService.addNewDestination(destination);
    }

    @GetMapping(value = "/destinations")
    public List<Destination> destinationListView() {
            return destinationService.findAll();
    }

    @PutMapping("/destination/update")
    public Destination destinationUpdate(@RequestBody Destination destination) {
        return destinationService.updateDestination(destination);
    }

    @DeleteMapping("/destination/delete/{id}")
    public ResponseEntity<Void> destinationDelete(@PathVariable String id) {
        return destinationService.deleteById(Long.valueOf(id));
    }

    @GetMapping("/destination/client/{date}")
    public List<String> findMostFrequentlyChosenDestination(@PathVariable String date) {
        return destinationService.findDestinationBuyByMostClient(LocalDate.parse(date));
    }
}
