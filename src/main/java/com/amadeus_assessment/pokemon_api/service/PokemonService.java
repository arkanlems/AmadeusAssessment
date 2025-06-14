package com.amadeus_assessment.pokemon_api.service;

import com.amadeus_assessment.pokemon_api.entity.Pokemon;
import com.amadeus_assessment.pokemon_api.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository repo;

    public List<Pokemon> findAll() {
        return repo.findAll();
    }

    public Optional<Pokemon> findById(Long id) {
        return repo.findById(id);
    }

    public Pokemon save(Pokemon p) {
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
