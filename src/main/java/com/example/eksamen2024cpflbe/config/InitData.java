
/*

package com.example.eksamen2024cpflbe.config;

import com.example.eksamen2024cpflbe.models.Guest;
import com.example.eksamen2024cpflbe.models.Hotel;
import com.example.eksamen2024cpflbe.models.Reservation;
import com.example.eksamen2024cpflbe.models.Room;
import com.example.eksamen2024cpflbe.repositories.GuestRepository;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import com.example.eksamen2024cpflbe.repositories.ReservationRepository;
import com.example.eksamen2024cpflbe.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Component
public class InitData implements CommandLineRunner
{


    @Autowired
    RandomCityGenerator randomCityGenerator;
    Random random = new Random();
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    //sikrer, at hele metoden kører inden for samme transaktion / session. Dette giver mulighed for at lazy load entiteter. Ellers udløber sessionen efter
    // Ellers udløbet sessionen efter for loopet og vi kan ikke loade rooms fra hotel da de er lazy loaded.
    @Override
    public void run(String... args) throws Exception
    {
        for ( int i = 0; i < 10; i++ ) // skal være 250 - men sætter til 10 så det ikke tager så lang tid
        {
            Hotel hotel = new Hotel();
            hotel.setName("Hotel " + (i + 1));
            hotel.setAddress("Hotelstreet " + (i + 1));
            hotel.setCity(randomCityGenerator.getRandomCity());
            hotel.setZip(1000 + random.nextInt(9000));
            hotel.setEmail("hotel" + i + "@hotel.com");
            hotel.setPhone(10000000 + random.nextInt(90000000));
            hotel.setDescription("Welcome to Hotel" + (i + 1));
            hotel.setCreated(LocalDateTime.now());
            hotel.setUpdated(LocalDateTime.now());

            hotelRepository.save(hotel);

            int roomCount = 10 + random.nextInt(30); // Fra 10 til 40
            for ( int j = 0; j < roomCount; j++ )
            {
                Room room = new Room();
                room.setHotel(hotel);
                room.setHotel(hotel);
                room.setPrice(50 + random.nextInt(101));
                room.setRoomNumber(j + 1);
                room.setDescription("Welcome to Roomnumber: " + (j + 1));
                room.setStatus(false);
                room.setCreated(LocalDateTime.now());
                room.setUpdated(LocalDateTime.now());

                room.setNumberOfBeds(1 + random.nextInt(4));
                if(room.getNumberOfBeds() == 1)
                {
                    room.setType("Single room");
                }
                else if(room.getNumberOfBeds() == 2)
                {
                    room.setType("Double room");
                }
                else if(room.getNumberOfBeds() == 3)
                {
                    room.setType("Three-bed room");
                }
                else if(room.getNumberOfBeds() == 4)
                {
                    room.setType("Four-bed room");
                }
                else
                {
                    room.setType("Unknown");
                }

                roomRepository.save(room);
            }
        }

        Guest guest = new Guest();
        guest.setFirstName("Christian");
        guest.setLastName("Lorenzen");
        guest.setEmail("lorider@coolkid.com");
        guest.setPhone (12345678);
        guest.setUsername("root");
        guest.setPassword("Sesame80");
        guest.setCreated(LocalDateTime.now());
        guest.setUpdated(LocalDateTime.now());
        guestRepository.save(guest);
    }
}

*/