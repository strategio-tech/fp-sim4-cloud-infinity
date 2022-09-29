package com.cloudinfinity.pokemonapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable(value = "id") long id) {
        
        // Optional<Pokemon> pokemon = pokemonRepository.findByPokemonID(id);

        return null;
    }
}
