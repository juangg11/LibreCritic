package com.videogame.librecritic.config;

import com.videogame.librecritic.model.Role;
import com.videogame.librecritic.model.User;
import com.videogame.librecritic.model.Videogame;
import com.videogame.librecritic.repository.RoleRepository;
import com.videogame.librecritic.repository.UserRepository;
import com.videogame.librecritic.repository.VideogameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(VideogameRepository repository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
            }

            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setRoles(Collections.singletonList(adminRole));
                userRepository.save(admin);
                System.out.println("ADMIN USER CREATED: admin / password");
            }

            if (repository.count() == 0) {
                Videogame g1 = new Videogame(null, 
                    "The Witcher 3: Wild Hunt", 
                    "You are Geralt of Rivia, mercenary monster slayer. Before you stands a war-torn, monster-infested continent you can explore at will. Your current contract? Tracking down Ciri — the Child of Prophecy, a living weapon that can alter the shape of the world.", 
                    "CD Projekt Red", 
                    LocalDate.of(2015, 5, 18), 
                    "thewitcher.jpg");

                Videogame g2 = new Videogame(null, 
                    "Red Dead Redemption 2", 
                    "Winner of over 175 Game of the Year Awards and recipient of over 250 perfect scores, RDR2 is the epic tale of outlaw Arthur Morgan and the infamous Van der Linde gang, on the run across America at the dawn of the modern age.", 
                    "Rockstar Games", 
                    LocalDate.of(2018, 10, 26), 
                    "readdead.avif");

                Videogame g3 = new Videogame(null, 
                    "Grand Theft Auto V", 
                    "When a young street hustler, a retired bank robber and a terrifying psychopath find themselves entangled with some of the most frightening and deranged elements of the criminal underworld, the U.S. government and the entertainment industry, they must pull off a series of dangerous heists to survive in a ruthless city in which they can trust nobody, least of all each other.", 
                    "Rockstar North", 
                    LocalDate.of(2013, 9, 17), 
                    "gta5.jpeg");

                Videogame g4 = new Videogame(null, 
                    "Portal 2", 
                    "The \"Single Player\" portion of Portal 2 introduces a cast of dynamic new characters, a host of fresh puzzle elements, and a much larger set of devious test chambers. Players will explore never-before-seen areas of the Aperture Science Labs and be reunited with GLaDOS, the occasionally murderous computer companion who guided them through the original game.", 
                    "Valve", 
                    LocalDate.of(2011, 4, 18), 
                    "portal2.jfif");

                Videogame g5 = new Videogame(null, 
                    "The Elder Scrolls V: Skyrim", 
                    "The Empire of Tamriel is on the edge. The High King of Skyrim has been murdered. Alliances form as claims to the throne are made. In the midst of this conflict, a far more dangerous, ancient evil is awakened. Dragons, long lost to the passages of the Elder Scrolls, have returned to Tamriel. The future of Skyrim, even the Empire itself, hangs in the balance as they wait for the prophesized Dragonborn to come; a hero born with the power of The Voice, and the only one who can stand amongst the dragons.", 
                    "Bethesda Game Studios", 
                    LocalDate.of(2011, 11, 11), 
                    "skyrinm.png");

                Videogame g6 = new Videogame(null, 
                    "Hollow Knight", 
                    "Forge your own path in Hollow Knight! An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.", 
                    "Team Cherry", 
                    LocalDate.of(2017, 2, 24), 
                    "hollowknigh.webp");
                
                Videogame g7 = new Videogame(null, 
                    "God of War (2018)", 
                    "His vengeance against the Gods of Olympus years behind him, Kratos now lives as a man in the realm of Norse Gods and monsters. It is in this harsh, unforgiving world that he must fight to survive… and teach his son to do the same.", 
                    "Santa Monica Studio", 
                    LocalDate.of(2018, 4, 20), 
                    "godofwar.webp");

                Videogame g8 = new Videogame(null, 
                    "Elden Ring", 
                    "The Golden Order has been broken. Rise, Tarnished, and be guided by grace to brandish the power of the Elden Ring and become an Elden Lord in the Lands Between.", 
                    "FromSoftware", 
                    LocalDate.of(2022, 2, 25), 
                    "eldenring.jpeg");

                Videogame g9 = new Videogame(null, 
                    "Bloodborne", 
                    "A fast-paced action RPG in a cursed, gothic city where hunters face horrific beasts and cosmic horrors.", 
                    "FromSoftware", 
                    LocalDate.of(2015, 3, 24), 
                    "bloodborne.jpg");

                Videogame g10 = new Videogame(null, 
                    "Persona 5 Royal", 
                    "Join the Phantom Thieves of Hearts as they navigate the supernatural Metaverse and Tokyo high school life.", 
                    "Atlus", 
                    LocalDate.of(2019, 10, 31), 
                    "persona.jfif");

                Videogame g11 = new Videogame(null, 
                    "Cyberpunk 2077", 
                    "An open-world, action-adventure story set in Night City, a megalopolis obsessed with power, glamour and body modification.", 
                    "CD Projekt Red", 
                    LocalDate.of(2020, 12, 10), 
                    "cyberpunk.jfif");

                Videogame g12 = new Videogame(null, 
                    "Super Mario Odyssey", 
                    "Join Mario on a massive, globe-trotting 3D adventure with his new companion Cappy.", 
                    "Nintendo", 
                    LocalDate.of(2017, 10, 27), 
                    "marioodyssey.jpg");

                repository.saveAll(List.of(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12));
                
                System.out.println("DEFAULT VIDEOGAMES LOADED SUCCESSFULLY");
            }

            Map<String, String> coverMap = Map.ofEntries(
                    Map.entry("The Witcher 3: Wild Hunt", "thewitcher.jpg"),
                    Map.entry("Red Dead Redemption 2", "readdead.avif"),
                    Map.entry("Grand Theft Auto V", "gta5.jpeg"),
                    Map.entry("Portal 2", "portal2.jfif"),
                    Map.entry("The Elder Scrolls V: Skyrim", "skyrinm.png"),
                    Map.entry("Hollow Knight", "hollowknigh.webp"),
                    Map.entry("God of War (2018)", "godofwar.webp"),
                    Map.entry("Elden Ring", "eldenring.jpeg"),
                    Map.entry("Bloodborne", "bloodborne.jpg"),
                    Map.entry("Persona 5 Royal", "persona.jfif"),
                    Map.entry("Cyberpunk 2077", "cyberpunk.jfif"),
                    Map.entry("Super Mario Odyssey", "marioodyssey.jpg")
            );
            repository.findAll().stream()
                    .filter(v -> coverMap.containsKey(v.getTitle()))
                    .forEach(v -> {
                        String expected = coverMap.get(v.getTitle());
                        if (!expected.equals(v.getCoverImagePath())) {
                            v.setCoverImagePath(expected);
                            repository.save(v);
                        }
                    });
        };
    }
}
