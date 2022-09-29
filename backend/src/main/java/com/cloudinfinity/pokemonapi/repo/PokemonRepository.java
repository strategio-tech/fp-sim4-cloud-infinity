package com.cloudinfinity.pokemonapi.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudinfinity.pokemonapi.model.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
    public Optional<Pokemon> findByPokemonID(long pokemonID);
}
