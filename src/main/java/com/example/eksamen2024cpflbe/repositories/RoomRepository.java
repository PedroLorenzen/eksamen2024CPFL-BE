package com.example.eksamen2024cpflbe.repositories;

import com.example.eksamen2024cpflbe.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer>
{
    Optional<Room> findByHotelIdAndRoomNumber(int id, int roomNumber);

    List<Room> findRoomsByHotelId(int hotelId);
}
