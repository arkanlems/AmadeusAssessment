package com.amadeus_assessment.pokemon_api.controller;

import com.amadeus_assessment.pokemon_api.entity.Pokemon;
import com.amadeus_assessment.pokemon_api.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonController {
    @Autowired
    private PokemonService service;

    @Operation(summary = "Get all Pokémon", description = "Retrieve a list of all Pokémon in the database.")
    @ApiResponse(responseCode = "200", description = "List of Pokémon successfully retrieved")
    @GetMapping
    public List<Pokemon> all() {
        return service.findAll();
    }


    @Operation(summary = "Get a Pokémon by ID", description = "Retrieve a single Pokémon by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Pokémon found")
    @ApiResponse(responseCode = "404", description = "Pokémon not found")
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> one( @Parameter(description = "ID of the Pokémon to retrieve") @PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Pokémon", description = "Add a new Pokémon to the database.")
    @ApiResponse(responseCode = "201", description = "Pokémon created successfully")
    @PostMapping
    public Pokemon create( @Parameter(description = "The Pokémon to create") @RequestBody Pokemon pokemon) {
        return service.save(pokemon);
    }

    @Operation(summary = "Update a Pokémon", description = "Update the details of an existing Pokémon by ID.")
    @ApiResponse(responseCode = "200", description = "Pokémon updated successfully")
    @ApiResponse(responseCode = "404", description = "Pokémon not found")
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@Parameter(description = "ID of the Pokémon to update") @PathVariable Long id,
                                          @Parameter(description = "Updated Pokémon data") @RequestBody Pokemon updated) {
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

    @Operation(summary = "Delete a Pokémon", description = "Remove a Pokémon from the database by ID.")
    @ApiResponse(responseCode = "204", description = "Pokémon deleted successfully")
    @ApiResponse(responseCode = "404", description = "Pokémon not found")
    @DeleteMapping("/{id}")
    public void delete( @Parameter(description = "ID of the Pokémon to delete")@PathVariable Long id) {
        service.delete(id);
    }
}
