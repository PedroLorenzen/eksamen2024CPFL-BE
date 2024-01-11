package com.example.eksamen2024cpflbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;
    String city;
    int zip;
    String email;
    int phoneNumber;
    String picture;
    String description;
    LocalDateTime created;
    LocalDateTime updated;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    private List<Reservation> reservations;
}
