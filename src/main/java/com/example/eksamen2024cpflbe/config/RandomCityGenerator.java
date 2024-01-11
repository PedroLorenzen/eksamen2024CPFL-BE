package com.example.eksamen2024cpflbe.config;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RandomCityGenerator
{
    private final List<String> cities;
    private final Random random;

    public RandomCityGenerator()
    {
        cities = Arrays.asList(
                "København", "Aarhus", "Odense", "Aalborg", "Esbjerg",
                "Randers", "Kolding", "Horsens", "Vejle", "Roskilde",
                "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
                "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
                "London", "Manchester", "Liverpool", "Edinburgh", "Glasgow",
                "Paris", "Marseille", "Lyon", "Toulouse", "Nice",
                "Berlin", "München", "Frankfurt", "Hamburg", "Stuttgart",
                "Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza",
                "Roma", "Milano", "Napoli", "Torino", "Palermo",
                "Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide"
        );
        random = new Random();
    }

    public String getRandomCity()
    {
        return cities.get(random.nextInt(cities.size()));
    }
}

