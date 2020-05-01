package pl.jwichniarek.travel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Destination {

    @Id
    @GeneratedValue(generator = "destinationSeq")
    @SequenceGenerator(name = "destinationSeq", sequenceName = "destination_seq", allocationSize = 1)
    private Long id;
    private String destinationName;
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(targetEntity = Trip.class, fetch = FetchType.LAZY)
    private Trip trip;

    public Destination() {
    }

    public Destination(Long id, String destinationName) {
        this.id = id;
        this.destinationName = destinationName;
    }

    public Destination(String destinationName, Trip trip) {
        this.destinationName = destinationName;
        this.trip = trip;
    }

    public Destination(Long id, String destinationName, Trip trip) {
        this.id = id;
        this.destinationName = destinationName;
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(destinationName, that.destinationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationName);
    }

}
