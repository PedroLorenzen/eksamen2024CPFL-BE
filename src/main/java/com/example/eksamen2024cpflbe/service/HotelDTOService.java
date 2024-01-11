package com.example.eksamen2024cpflbe.service;

import com.example.eksamen2024cpflbe.DTO.HotelDTO;
import com.example.eksamen2024cpflbe.models.Hotel;
import org.springframework.stereotype.Service;

@Service
public class HotelDTOService
{
    // Denne metode konverterer et Hotel-objekt til et GetHotelDTO-objekt
    public HotelDTO convertHotelToDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());

        if (hotel.getRooms() != null) {
            dto.setRooms(hotel.getRooms().size());
        } else {
            dto.setRooms(0);
        }
        return dto;
    }
}
