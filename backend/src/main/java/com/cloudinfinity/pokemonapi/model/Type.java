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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(name = "slot")
    private long slot;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "types")
    @JsonIgnore
    private List<Pokemon> pokemon = new ArrayList<>();

    public Type() {
    }

    public Type(long slot, String name, List<Pokemon> pokemon) {
        this.slot = slot;
        this.name = name;
        this.pokemon = pokemon;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSlot() {
        return this.slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getPokemon() {
        return this.pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public Type id(long id) {
        setId(id);
        return this;
    }

    public Type slot(long slot) {
        setSlot(slot);
        return this;
    }

    public Type name(String name) {
        setName(name);
        return this;
    }

    public Type pokemon(List<Pokemon> pokemon) {
        setPokemon(pokemon);
        return this;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Type)) {
            return false;
        }
        Type type = (Type) o;
        return id == type.id && slot == type.slot && Objects.equals(name, type.name)
                && Objects.equals(pokemon, type.pokemon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slot, name, pokemon);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", slot='" + getSlot() + "'" +
                ", name='" + getName() + "'" +
                ", pokemon='" + getPokemon() + "'" +
                "}";
    }

}