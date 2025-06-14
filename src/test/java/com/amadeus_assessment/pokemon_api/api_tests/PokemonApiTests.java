package com.amadeus_assessment.pokemon_api.api_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class PokemonApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllPokemons() throws Exception {

    }
}
