package com.videogame.librecritic.repository;

import com.videogame.librecritic.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Long> {
}
