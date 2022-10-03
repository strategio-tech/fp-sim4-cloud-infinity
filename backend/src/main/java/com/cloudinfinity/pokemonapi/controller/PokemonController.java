package com.cloudinfinity.pokemonapi.controller;

import java.lang.StackWalker.Option;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cloudinfinity.pokemonapi.model.Pokemon;
import com.cloudinfinity.pokemonapi.repo.PokemonRepository;
import com.cloudinfinity.pokemonapi.utils.ThirdParty;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping("/{pokemonID}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable(value = "pokemonID") long pokemonID) {
        if(pokemonID < 1 || pokemonID > 906) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Pokemon> pokemon = pokemonRepository.findByPokemonID(pokemonID);

        if(!pokemon.isPresent()) {
            // The pokemon is not found
            // Call third-party API
            System.out.println("This pokemon is not in the database: " + pokemonID);
            
            Pokemon pokemonFromAPI = ThirdParty.getPokemon(pokemonID);
            pokemonRepository.save(pokemonFromAPI);

            return new ResponseEntity<>(pokemonFromAPI, HttpStatus.OK);
        }

        return new ResponseEntity<>(pokemon.get(), HttpStatus.OK);
    }
}
