package com.example.eksamen2024cpflbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int hotelId;
    int roomNumber;
    int numberOfBeds; // 1-4
    int price;
    String picture;
    String description;
    String type;
    boolean status;
    Date created;
    Date updated;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id")
    private Hotel hotel;
}
