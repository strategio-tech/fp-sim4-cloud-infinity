package com.cloudinfinity.pokemonapi.model;

import javax.persistence.Column;
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
    private long id;

    @Column(name="pokemonID")
    private long pokemonID;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="height")
    private long height;

    @Column(name="weight")
    private long weight;

    @Column(name="is_legendary")
    private boolean isLegendary;

    @Column(name="is_mythical")
    private boolean isMythical;

    @Column(name="evolution_chain_URL")
    private String evolutionChainURL;

    @Column(name="color")
    private String color;

    @Column(name="image")
    private String image;

    public Pokemon(long id, long pokemonID, String name, String description, int height, int weight, boolean isLegendary, boolean isMythical, String evolutionChainURL, String color, String image) {
        this.id = id;
        this.pokemonID = pokemonID;
        this.name = name;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.isLegendary = isLegendary;
        this.isMythical = isMythical;
        this.evolutionChainURL = evolutionChainURL;
        this.color = color;
        this.image = image;
    }

    public Pokemon() {
        this(-1, -1, "", "", -1, -1, false, false, "", "", "");
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPokemonID() {
        return this.pokemonID;
    }

    public void setPokemonID(long pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getHeight() {
        return this.height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return this.weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public boolean isIsLegendary() {
        return this.isLegendary;
    }

    public boolean getIsLegendary() {
        return this.isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public boolean isIsMythical() {
        return this.isMythical;
    }

    public boolean getIsMythical() {
        return this.isMythical;
    }

    public void setIsMythical(boolean isMythical) {
        this.isMythical = isMythical;
    }

    public String getEvolutionChainURL() {
        return this.evolutionChainURL;
    }

    public void setEvolutionChainURL(String evolutionChainURL) {
        this.evolutionChainURL = evolutionChainURL;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}