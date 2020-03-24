package com.example.travel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Trip {

    @Id
    @GeneratedValue(generator = "trip_seq")
    @SequenceGenerator(name = "trip_seq", sequenceName = "trip_seq", allocationSize = 1)
    private Long id;
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(targetEntity = Destination.class, fetch = FetchType.LAZY)
    private Destination destination;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfReturn;
    private BigDecimal suggestedPrice;
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = TravelGuide.class, fetch = FetchType.LAZY)
    private TravelGuide guide;
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(targetEntity = Reservation.class, fetch = FetchType.LAZY)
    private List<Reservation> reservation;

    public Trip() {
    }

    public Trip(Long id, LocalDate dateOfDeparture, LocalDate dateOfReturn, BigDecimal suggestedPrice) {
        this.id = id;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfReturn = dateOfReturn;
        this.suggestedPrice = suggestedPrice;
    }

    public Trip(Long id, Destination destination, LocalDate dateOfDeparture, LocalDate dateOfReturn, BigDecimal suggestedPrice, TravelGuide guide) {
        this.id = id;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfReturn = dateOfReturn;
        this.suggestedPrice = suggestedPrice;
        this.guide = guide;
    }

    public Trip(Long id, Destination destination, LocalDate dateOfDeparture, LocalDate dateOfReturn, BigDecimal suggestedPrice, TravelGuide guide, List<Reservation> reservation) {
        this.id = id;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfReturn = dateOfReturn;
        this.suggestedPrice = suggestedPrice;
        this.guide = guide;
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public BigDecimal getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(BigDecimal suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public TravelGuide getGuide() {
        return guide;
    }

    public void setGuide(TravelGuide guide) {
        this.guide = guide;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) &&
                Objects.equals(dateOfDeparture, trip.dateOfDeparture) &&
                Objects.equals(dateOfReturn, trip.dateOfReturn) &&
                Objects.equals(suggestedPrice, trip.suggestedPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfDeparture, dateOfReturn, suggestedPrice);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", dateOfDeparture=" + dateOfDeparture +
                ", dateOfReturn=" + dateOfReturn +
                ", suggestedPrice=" + suggestedPrice +
                '}';
    }
}
