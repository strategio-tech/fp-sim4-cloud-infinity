package com.cloudinfinity.pokemonapi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
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

    @Column(name="species")
    private String species;

    @Column(name="is_legendary")
    private boolean isLegendary;

    @Column(name="is_mythical")
    private boolean isMythical;

    @Column(name="evolution_chain_URL")
    private String evolutionChainURL;

    @Column(name="color")
    private String color;

    @Column(name="image")
    // @Lob
    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "pokemon_types", joinColumns = { @JoinColumn(name = "pokemon_id") }, inverseJoinColumns = {
            @JoinColumn(name = "type_id") })
    private List<Type> types = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "pokemon_id")
    private Set<Stat> stats = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "pokemon_id")
    private Set<Ability> abilities = new HashSet<>();

    public Pokemon(long pokemonID, String name, String description, long height, long weight, String species, boolean isLegendary, boolean isMythical, String evolutionChainURL, String color, String image) {
        this.pokemonID = pokemonID;
        this.name = name;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.species = species;
        this.isLegendary = isLegendary;
        this.isMythical = isMythical;
        this.evolutionChainURL = evolutionChainURL;
        this.color = color;
        this.image = image;
    }

    public Pokemon() {
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

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
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

    public List<Type> getTypes() {
        return this.types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Set<Stat> getStats() {
        return this.stats;
    }

    public void setStats(Set<Stat> stats) {
        this.stats = stats;
    }

    public Set<Ability> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public void addType(Type type) {
        this.types.add(type);
        type.getPokemon().add(this);
    }

    public void removeType(long typeId) {
        Type type = this.types.stream().filter(t -> t.getId() == typeId).findFirst().orElse(null);

        if(type != null) {
            this.types.remove(type);
            type.getPokemon().remove(this);
        }
    }

    public void addStat(Stat stat) {
        this.stats.add(stat);
    }

    public void removeStat(long statId) {
        Stat stat = this.stats.stream().filter(s -> s.getId() == statId).findFirst().orElse(null);

        if(stat != null) {
            this.stats.remove(stat);
        }
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public void removeAbility(long abilityId) {
        Ability ability = this.abilities.stream().filter(a -> a.getId() == abilityId).findFirst().orElse(null); 

        if(ability != null) {
            this.abilities.remove(ability);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id && pokemonID == pokemon.pokemonID && Objects.equals(name, pokemon.name) && Objects.equals(description, pokemon.description) && height == pokemon.height && weight == pokemon.weight && Objects.equals(species, pokemon.species) && isLegendary == pokemon.isLegendary && isMythical == pokemon.isMythical && Objects.equals(evolutionChainURL, pokemon.evolutionChainURL) && Objects.equals(color, pokemon.color) && Objects.equals(image, pokemon.image) && Objects.equals(types, pokemon.types) && Objects.equals(stats, pokemon.stats) && Objects.equals(abilities, pokemon.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pokemonID, name, description, height, weight, species, isLegendary, isMythical, evolutionChainURL, color, image, types, stats, abilities);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", pokemonID='" + getPokemonID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", height='" + getHeight() + "'" +
            ", weight='" + getWeight() + "'" +
            ", species='" + getSpecies() + "'" +
            ", isLegendary='" + isIsLegendary() + "'" +
            ", isMythical='" + isIsMythical() + "'" +
            ", evolutionChainURL='" + getEvolutionChainURL() + "'" +
            ", color='" + getColor() + "'" +
            ", image='" + getImage() + "'" +
            ", types='" + getTypes() + "'" +
            ", stats='" + getStats() + "'" +
            ", abilities='" + getAbilities() + "'" +
            "}";
    }

}