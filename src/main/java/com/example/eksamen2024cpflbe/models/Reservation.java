package com.example.eksamen2024cpflbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int guests;
    int price; //prisen her må ikke tilpasse sig hvis room price ændrer sig - ikke sat op til at referere til room price
    Date reservationDate;
    Date startDate;
    Date endDate;
    LocalDateTime created;
    LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "guestId", referencedColumnName = "id")
    @JsonBackReference("guest-reservation")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    @JsonBackReference("hotel-reservation")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    @JsonBackReference("room-reservation")
    private Room room;
}
