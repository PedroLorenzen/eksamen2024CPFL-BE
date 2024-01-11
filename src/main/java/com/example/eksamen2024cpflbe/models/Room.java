package com.example.eksamen2024cpflbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int roomNumber;
    int capacity; // 1-4
    int price;
    String picture;
    String description;
    String type;
    boolean status;
    LocalDateTime created;
    LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    private Hotel hotel;
}
