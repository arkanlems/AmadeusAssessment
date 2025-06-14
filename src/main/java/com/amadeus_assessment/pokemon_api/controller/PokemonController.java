package com.amadeus_assessment.pokemon_api.controller;

import com.amadeus_assessment.pokemon_api.entity.Pokemon;
import com.amadeus_assessment.pokemon_api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService service;

    @GetMapping
    public List<Pokemon> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> one(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pokemon create(@RequestBody Pokemon pokemon) {
        return service.save(pokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable Long id, @RequestBody Pokemon updated) {
        return service.findById(id)
                .map(p -> {
                    p.setName(updated.getName());
                    p.setType(updated.getType());
                    p.setLevel(updated.getLevel());
                    p.setLegendary(updated.isLegendary());
                    return ResponseEntity.ok(service.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
