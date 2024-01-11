package com.example.eksamen2024cpflbe.repositories;

import com.example.eksamen2024cpflbe.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Integer>
{
    Optional<Hotel> findByName(String name);
}
