package com.example.eksamen2024cpflbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int roomNumber;
    int numberOfBeds; // 1-4
    int price;
    String picture;
    String description;
    String type;
    boolean status;
    LocalDateTime created;
    LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    @JsonBackReference("hotel-room")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    @JsonBackReference("room-reservation")
    private List<Reservation> reservations;

}
