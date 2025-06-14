package com.amadeus_assessment.pokemon_api.controller;

import com.amadeus_assessment.pokemon_api.entity.Pokemon;
import com.amadeus_assessment.pokemon_api.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.ResponseEntity;

class PokemonControllerTest {

    private PokemonService service;
    private PokemonController controller;

    @BeforeEach
    void setUp() {
        service = mock(PokemonService.class);
        controller = new PokemonController(service);
    }

    @Test
    void testGetAll() {
        Pokemon p1 = new Pokemon(1L, "Bulbasaur", "Grass", 5, false);
        when(service.findAll()).thenReturn(List.of(p1));

        List<Pokemon> result = controller.all();

        assertEquals(1, result.size());
        assertEquals("Bulbasaur", result.get(0).getName());
        verify(service).findAll();
    }

    @Test
    void testGetOneFound() {
        Pokemon p = new Pokemon(2L, "Charmander", "Fire", 10, false);
        when(service.findById(2L)).thenReturn(Optional.of(p));

        ResponseEntity<Pokemon> response = controller.one(2L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Charmander", response.getBody().getName());
        verify(service).findById(2L);
    }

    @Test
    void testGetOneNotFound() {
        when(service.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Pokemon> response = controller.one(99L);

        assertEquals(404, response.getStatusCode().value());
        verify(service).findById(99L);
    }

    @Test
    void testCreate() {
        Pokemon input = new Pokemon(null, "Squirtle", "Water", 7, false);
        Pokemon saved = new Pokemon(3L, "Squirtle", "Water", 7, false);
        when(service.save(input)).thenReturn(saved);

        Pokemon result = controller.create(input);

        assertEquals(3L, result.getId());
        verify(service).save(input);
    }

    @Test
    void testUpdateFound() {
        Pokemon existing = new Pokemon(4L, "Pikachu", "Electric", 8, false);
        Pokemon updated = new Pokemon(4L, "Raichu", "Electric", 12, false);
        when(service.findById(4L)).thenReturn(Optional.of(existing));
        when(service.save(any(Pokemon.class))).thenReturn(updated);

        ResponseEntity<Pokemon> response = controller.update(4L, updated);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Raichu", response.getBody().getName());
        assertEquals(12, response.getBody().getLevel());
        verify(service).findById(4L);
        verify(service).save(existing);
    }

    @Test
    void testUpdateNotFound() {
        when(service.findById(404L)).thenReturn(Optional.empty());

        ResponseEntity<Pokemon> response = controller.update(404L, new Pokemon());

        assertEquals(404, response.getStatusCode().value());
        verify(service).findById(404L);
    }

    @Test
    void testDelete() {
        doNothing().when(service).delete(5L);

        controller.delete(5L);

        verify(service).delete(5L);
    }
}