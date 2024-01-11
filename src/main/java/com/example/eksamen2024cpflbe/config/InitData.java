package com.example.eksamen2024cpflbe.config;

import com.example.eksamen2024cpflbe.models.Hotel;
import com.example.eksamen2024cpflbe.models.Room;
import com.example.eksamen2024cpflbe.repositories.HotelRepository;
import com.example.eksamen2024cpflbe.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    @Override
    public void run(String... args) throws Exception
    {
        for ( int i = 0; i < 10; i++ ) // skal være 250 - men sætter til 10 så det ikke tager så lang tid
        {
            Hotel hotel = new Hotel();
            hotel.setName("Hotel " + i);
            hotel.setAddress("Hotelstreet " + i);
            hotel.setCity(randomCityGenerator.getRandomCity());
            hotel.setZip(1000 + random.nextInt(9000));
            hotel.setEmail("hotel" + i + "@hotel.com");
            hotel.setPhoneNumber(10000000 + random.nextInt(90000000));
            hotel.setDescription("Welcome to Hotel" + i);
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

                room.setCapacity(1 + random.nextInt(4));
                if(room.getCapacity() == 1)
                {
                    room.setType("Single room");
                }
                else if(room.getCapacity() == 2)
                {
                    room.setType("Double room");
                }
                else if(room.getCapacity() == 3)
                {
                    room.setType("Three-bed room");
                }
                else if(room.getCapacity() == 4)
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
    }
}

