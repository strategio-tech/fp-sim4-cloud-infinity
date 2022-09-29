package com.cloudinfinity.pokemonapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinfinity.pokemonapi.model.Pokemon;
import com.cloudinfinity.pokemonapi.repo.PokemonRepository;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping("/{pokemonID}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable(value = "pokemonID") long pokemonID) {
        
        // Optional<Pokemon> pokemon = pokemonRepository.findByPokemonID(id);
//        Pokemon p = pokemonRepository.findByPokemonID(pokemonID)
//            .orElseThrow(() -> new RuntimeException("Pokemon not found"));

//        return new ResponseEntity<>(p, HttpStatus.OK);

        Pokemon poke = new Pokemon();
        poke.setName("Jordan");

        return new ResponseEntity<>(poke, HttpStatus.OK);
    }

    @GetMapping("/test/hi")
    public String test() {
        return "hi";
    }
}
