package com.example.eksamen2024cpflbe.controllers;

import com.example.eksamen2024cpflbe.models.Hotel;
import com.example.eksamen2024cpflbe.models.Room;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import com.example.eksamen2024cpflbe.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class RoomRestController
{
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("hotel/{hotelId}/room/{roomNumber}")
    public ResponseEntity<Room> getRoomByHotelAndRoomNumber(@PathVariable int hotelId, @PathVariable int roomNumber)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if ( hotelOptional.isPresent() )
        {
            Optional<Room> roomOptional = roomRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber);
            if ( roomOptional.isPresent() )
            {
                System.out.println("Room with room-number: " + roomNumber + " in the hotel with id: " + hotelId + " requested");
                return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
            }
            else
            {
                System.out.println("Room not found in the database");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        else
        {
            System.out.println("Hotel not found in the database");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hotel/{id}/rooms")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable int id)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            List<Room> rooms = roomRepository.findRoomsByHotelId(id);
            System.out.println("Rooms for hotel with id: " + id + " requested");
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/hotel/{id}/room")
    public ResponseEntity<Room> createRoom(@PathVariable int id, @RequestBody Room room)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            Optional<Room> existingRoom = roomRepository.findByHotelIdAndRoomNumber(id, room.getRoomNumber());
            if ( existingRoom.isPresent() )
            {
                System.out.println("Room already exists in the database");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            else
            {
                room.setHotel(hotelOptional.get());
                room.setCreated(LocalDateTime.now());
                room.setUpdated(LocalDateTime.now());
                Room savedRoom = roomRepository.save(room);
                String hotelName = hotelOptional.get().getName();
                System.out.println("Room with room-number: " + savedRoom.getRoomNumber() + " in the hotel: " + hotelName + " has been saved to the database");
                return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
            }
        }
        else
        {
            System.out.println("Hotel not found in the database");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
