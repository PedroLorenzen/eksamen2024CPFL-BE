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

// Use MockitoExtension with JUnit 5 for Mockito support - automatically creates mocks and injects them
@ExtendWith(MockitoExtension.class)
class Eksamen2024CpflBeApplicationTests {

    // Create a mock implementation of HotelRepository
    @Mock
    private HotelRepository hotelRepository;

    // Create an instance of HotelRestController and inject the mock HotelRepository into it
    @InjectMocks
    private HotelRestController hotelController;

    // Test case: Check the behavior when a hotel with the given ID exists
    @Test
    void whenHotelExists_thenRetrieveHotel() {
        int id = 1; // Define a sample hotel ID
        Hotel hotel = new Hotel(); // Create a mock Hotel object

        // Define behavior: when findById is called on the repository, return an Optional of hotel
        when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));

        // Call the method under test and store the response
        ResponseEntity<Hotel> response = hotelController.getFullHotelById(id);

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Assert that the response body is the hotel object we set up earlier
        assertEquals(hotel, response.getBody());
        // Verify that findById was called on the repository mock
        verify(hotelRepository).findById(id);
    }

    // Test case: Check the behavior when no hotel with the given ID exists
    @Test
    void whenHotelDoesNotExist_thenNotFound() {
        int id = 1; // Define a sample hotel ID

        // Define behavior: when findById is called on the repository, return an empty Optional
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method under test and store the response
        ResponseEntity<Hotel> response = hotelController.getFullHotelById(id);

        // Assert that the response status is NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Assert that the response body is null
        assertNull(response.getBody());
        // Verify that findById was called on the repository mock
        verify(hotelRepository).findById(id);
    }
}

