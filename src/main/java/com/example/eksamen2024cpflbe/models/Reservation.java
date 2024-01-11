package com.example.eksamen2024cpflbe.models;

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
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;
}
