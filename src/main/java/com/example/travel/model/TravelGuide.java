package com.example.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class TravelGuide {

    @Id
    @GeneratedValue(generator = "guide_seq")
    @SequenceGenerator(name = "guide_seq", sequenceName = "guide_seq", allocationSize = 1)
    private Long id;
    private String name;

    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(targetEntity = Trip.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "travel_guide_trips", joinColumns = @JoinColumn(name = "guide_id"))
    private List<Trip> trips;

    public TravelGuide() {
    }

    public TravelGuide(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TravelGuide(Long id, String name, List<Trip> trips) {
        this.id = id;
        this.name = name;
        this.trips = trips;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelGuide that = (TravelGuide) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TravelGuide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}