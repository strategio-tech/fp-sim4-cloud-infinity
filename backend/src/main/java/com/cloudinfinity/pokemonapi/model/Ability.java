package com.cloudinfinity.pokemonapi.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "abilities")
public class Ability {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="is_hidden")
    private boolean isHidden;

    @Column(name="slot")
    private long slot;

    public Ability() {
    }

    public Ability(long id, String name, boolean isHidden, long slot) {
        this.id = id;
        this.name = name;
        this.isHidden = isHidden;
        this.slot = slot;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsHidden() {
        return this.isHidden;
    }

    public boolean getIsHidden() {
        return this.isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public long getSlot() {
        return this.slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }

    public Ability id(long id) {
        setId(id);
        return this;
    }

    public Ability name(String name) {
        setName(name);
        return this;
    }

    public Ability isHidden(boolean isHidden) {
        setIsHidden(isHidden);
        return this;
    }

    public Ability slot(long slot) {
        setSlot(slot);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ability)) {
            return false;
        }
        Ability ability = (Ability) o;
        return id == ability.id && name == ability.name && isHidden == ability.isHidden && slot == ability.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isHidden, slot);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", isHidden='" + isIsHidden() + "'" +
            ", slot='" + getSlot() + "'" +
            "}";
    }

}
