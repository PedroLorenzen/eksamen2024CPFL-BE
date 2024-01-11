package com.example.eksamen2024cpflbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
public class Guest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    String email;
    int phoneNumber;
    String username;
    String password;
    Date updated;
    Date created;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;

}
