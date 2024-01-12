package com.example.eksamen2024cpflbe;

import com.example.eksamen2024cpflbe.controllers.HotelRestController;
import com.example.eksamen2024cpflbe.models.Hotel;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Eksamen2024CpflBeApplicationTests {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelRestController hotelController;

    @Test
    void whenHotelExists_thenRetrieveHotel() {
        int id = 1; // Define a sample hotel ID
        Hotel hotel = new Hotel(); // Create a mock Hotel object

        when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));

        ResponseEntity<Hotel> response = hotelController.getFullHotelById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotel, response.getBody());
        verify(hotelRepository).findById(id);
    }

    @Test
    void whenHotelDoesNotExist_thenNotFound() {
        int id = 1; // Define a sample hotel ID

        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Hotel> response = hotelController.getFullHotelById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(hotelRepository).findById(id);
    }
}

