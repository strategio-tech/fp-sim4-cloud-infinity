package com.cloudinfinity.pokemonapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    private final long pokemonID;
    private final String name;
    private final String description;
    private final int height;
    private final int weight;
    private final boolean is_legendary;
    private final boolean is_mythical;
    private final String evolution_chain_URL;
    private final String color;
    private final String image;

    public Pokemon(long id, long pokemonID, String name, String description, int height, int weight, boolean is_legendary, boolean is_mythical, String evolution_chain_URL, String color, String image) {
        this.id = id;
        this.pokemonID = pokemonID;
        this.name = name;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.is_legendary = is_legendary;
        this.is_mythical = is_mythical;
        this.evolution_chain_URL = evolution_chain_URL;
        this.color = color;
        this.image = image;
    }


    public long getId() {
        return this.id;
    }

    public long getPokemonID() {
        return this.pokemonID;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean getIs_legendary() {
        return this.is_legendary;
    }

    public boolean isIs_legendary() {
        return this.is_legendary;
    }

    public boolean getIs_mythical() {
        return this.is_mythical;
    }

    public boolean isIs_mythical() {
        return this.is_mythical;
    }

    public String getEvolution_chain_URL() {
        return this.evolution_chain_URL;
    }

    public String getColor() {
        return this.color;
    }

    public String getImage() {
        return this.image;
    }
    
}