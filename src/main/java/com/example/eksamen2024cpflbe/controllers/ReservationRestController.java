package com.example.eksamen2024cpflbe.controllers;

import com.example.eksamen2024cpflbe.models.Reservation;
import com.example.eksamen2024cpflbe.models.Room;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import com.example.eksamen2024cpflbe.repositories.ReservationRepository;
import com.example.eksamen2024cpflbe.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin
@RestController
public class ReservationRestController
{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation)
    {
        Optional<Room> roomOptional = roomRepository.findById(reservation.getRoom().getId());
        if ( roomOptional.isPresent() )
        {
            Optional<Reservation> existingReservation = reservationRepository.findByRoomIdAndReservationDate(reservation.getRoom().getId(), reservation.getReservationDate());
            if ( existingReservation.isPresent() )
            {
                System.out.println("The room is already booked on this date");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            reservation.setRoom(roomOptional.get());
            reservation.setCreated(LocalDateTime.now());
            reservation.setUpdated(LocalDateTime.now());
            Reservation savedReservation = reservationRepository.save(reservation);
            System.out.println("Reservation for room: " + savedReservation.getRoom().getRoomNumber() + " on date: " + savedReservation.getReservationDate() + ", has been saved to the database");

            return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
        }
        else
        {
            System.out.println("Room not found in the database");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
