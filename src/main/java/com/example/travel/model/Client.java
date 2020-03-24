package com.example.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator = "clientSeq")
    @SequenceGenerator(name = "clientSeq", sequenceName = "client_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String idNumber;
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
    private Reservation reservation;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String idNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }

    public Client(String firstName, String lastName, String idNumber, Reservation reservation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.reservation = reservation;
    }

    public Client(Long id, String firstName, String lastName, String idNumber, Reservation reservation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(idNumber, client.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, idNumber);
    }
}