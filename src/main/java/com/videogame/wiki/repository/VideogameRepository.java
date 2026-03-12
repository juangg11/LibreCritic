package com.videogame.wiki.repository;

import com.videogame.wiki.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Long> {
}
