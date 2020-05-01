package pl.jwichniarek.travel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "reservationSeq")
    @SequenceGenerator(name = "reservationSeq", sequenceName = "reservation_seq", allocationSize = 1)
    private Long id;
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Trip.class, fetch=FetchType.LAZY)
    private Trip trip;
    private BigDecimal negotiatedPrice;
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
    private Client client;

    public Reservation() {
    }

    public Reservation(Long id, BigDecimal negotiatedPrice) {
        this.id = id;
        this.negotiatedPrice = negotiatedPrice;
    }

    public Reservation(Trip trip, BigDecimal negotiatedPrice, Client client) {
        this.trip = trip;
        this.negotiatedPrice = negotiatedPrice;
        this.client = client;
    }

    public Reservation(Long id, Trip trip, BigDecimal negotiatedPrice, Client client) {
        this.id = id;
        this.trip = trip;
        this.negotiatedPrice = negotiatedPrice;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public BigDecimal getNegotiatedPrice() {
        return negotiatedPrice;
    }

    public void setNegotiatedPrice(BigDecimal negotiatedPrice) {
        this.negotiatedPrice = negotiatedPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(negotiatedPrice, that.negotiatedPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, negotiatedPrice);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", negotiatedPrice=" + negotiatedPrice +
                '}';
    }
}
