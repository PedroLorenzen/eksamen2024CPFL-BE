package com.example.eksamen2024cpflbe.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class ReservationDTO
{
    private int roomId;
    private int guestId;
    private int price;
    private Date reservationDate;
}
