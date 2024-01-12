package com.example.eksamen2024cpflbe.repositories;

import com.example.eksamen2024cpflbe.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>
{
    Optional<Reservation> findByRoomIdAndReservationDate(int roomId, Date reservationDate);


}
