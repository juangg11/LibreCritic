package com.videogame.wiki.service;

import com.videogame.wiki.model.Videogame;
import com.videogame.wiki.repository.VideogameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideogameService {

    private final VideogameRepository videogameRepository;

    public VideogameService(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    public List<Videogame> findAll() {
        return videogameRepository.findAll();
    }

    public Optional<Videogame> findById(Long id) {
        return videogameRepository.findById(id);
    }

    public Videogame save(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public void deleteById(Long id) {
        videogameRepository.deleteById(id);
    }
}
