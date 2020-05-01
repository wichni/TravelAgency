package pl.jwichniarek.travel.controller;

import pl.jwichniarek.travel.model.TravelGuide;
import pl.jwichniarek.travel.service.TravelGuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TravelGuideController {

    private final TravelGuideService travelGuideService;

    public TravelGuideController(TravelGuideService travelGuideService) {
        this.travelGuideService = travelGuideService;
    }

    @PostMapping("/guide/add")
    public TravelGuide addGuide(@RequestBody TravelGuide travelGuide) {
        return travelGuideService.addNewGuide(travelGuide);
    }

    @GetMapping("/guides")
    public List<TravelGuide> guideListView() {
        return travelGuideService.findAll();
    }

    @PutMapping("/guide/update/")
    public TravelGuide updateGuide(@RequestBody TravelGuide travelGuide) {
        return travelGuideService.updateGuide(travelGuide);
    }

    @DeleteMapping("/guide/delete/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable Long id) {
        return travelGuideService.deleteGuideById(id);
    }

    @GetMapping("/guide/date/{date}")
    public List<String> findGuideSoldMostTrips(@PathVariable String date) {
        return travelGuideService.findGuideWhoSoldTripsForBiggestPrice(LocalDate.parse(date));
    }
}
