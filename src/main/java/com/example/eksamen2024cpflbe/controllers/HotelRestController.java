package com.example.eksamen2024cpflbe.controllers;

import com.example.eksamen2024cpflbe.DTO.HotelDTO;
import com.example.eksamen2024cpflbe.models.Hotel;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import com.example.eksamen2024cpflbe.service.HotelDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class HotelRestController
{
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelDTOService hotelDTOService;

    @GetMapping("/dtohotels")
    public List<HotelDTO> getHotels()
    {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for ( Hotel hotel : allHotels )
        {
            HotelDTO hotelDTO = hotelDTOService.convertHotelToDTO(hotel);
            hotelDTOList.add(hotelDTO);
        }
        System.out.println("All hotels in DTO form requested");
        return hotelDTOList;
    }

    @GetMapping("/dtohotel/{id}")
    public ResponseEntity<HotelDTO> getHotelDTOById(@PathVariable int id)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            HotelDTO hotelDTO = hotelDTOService.convertHotelToDTO(hotelOptional.get());
            System.out.println("Hotel DTO with id: " + id + " requested");
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<Hotel> getFullHotelById(@PathVariable int id)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            System.out.println("Full hotel object with id: " + id + " requested for updating");
            return new ResponseEntity<>(hotelOptional.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        Optional<Hotel> existingHotel = hotelRepository.findByName(hotel.getName());

        if ( existingHotel.isPresent() )
        {
            System.out.println("Hotel already exists in the database");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else
        {
            hotel.setCreated(java.time.LocalDateTime.now());
            hotel.setUpdated(java.time.LocalDateTime.now());
            Hotel savedHotel = hotelRepository.save(hotel);
            System.out.println("Hotel with name: " + savedHotel.getName() + " and adress: " + savedHotel.getAddress() + ", has been saved to the database");
            return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
        }
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int id, @RequestBody Hotel hotel)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            hotel.setId(id);
            hotel.setUpdated(java.time.LocalDateTime.now());
            hotelRepository.save(hotel);
            System.out.println("The updated Hotel with id: " + hotel.getId() + ", name: " + hotel.getName() + " and adress: " + hotel.getAddress() + ", has been updated in the database");
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable int id)
    {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if ( hotelOptional.isPresent() )
        {
            Hotel hotel = hotelOptional.get();
            List<Integer> reservations = new ArrayList<>();

            /*if ( hotel.roo() != null )
            {
                for ( Reservation reservation : hotel.getReservations() )
                {
                    reservations.add(reservation.getId());
                }

                if ( !reservations.isEmpty() )
                {
                    System.out.println(reservations + " reservations are active. You can't delete the hotel");
                    return new ResponseEntity<>("Hotel with active reservations cannot be deleted", HttpStatus.BAD_REQUEST);
                }
            }*/

            hotelRepository.deleteById(id);
            System.out.println("Hotel with id: " + id + " has been deleted from the database");
            return new ResponseEntity<>("Hotel with id " + id + " deleted", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Hotel with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


}
