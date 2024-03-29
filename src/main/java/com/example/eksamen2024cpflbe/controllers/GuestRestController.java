package com.example.eksamen2024cpflbe.controllers;

import com.example.eksamen2024cpflbe.models.Guest;
import com.example.eksamen2024cpflbe.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class GuestRestController
{
    @Autowired
    GuestRepository guestRepository;

    @GetMapping("/guests")
    public List<Guest> getGuests()
    {
        List<Guest> allGuests = guestRepository.findAll();
        System.out.println("All guests has been requested");
        return allGuests;
    }

    @PostMapping("/guest")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest)
    {
        Optional<Guest> existingGuest = guestRepository.findByPhone(guest.getPhone());

        if ( existingGuest.isPresent() )
        {
            System.out.println("Guest already exists in the database");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else
        {
            guest.setCreated(java.time.LocalDateTime.now());
            guest.setUpdated(java.time.LocalDateTime.now());
            Guest savedGuest = guestRepository.save(guest);
            System.out.println("The guest " + savedGuest.getFirstName() + " " + savedGuest.getLastName() + ", with phone-number: " + savedGuest.getPhone() + " has been saved to the database");
            return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
        }
    }

}
