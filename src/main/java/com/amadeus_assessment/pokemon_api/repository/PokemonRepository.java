package com.amadeus_assessment.pokemon_api.repository;

import com.amadeus_assessment.pokemon_api.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
