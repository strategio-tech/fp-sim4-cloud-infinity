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
@Table(name = "stats")
public class Stat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    
    @Column(name="base_stat")
    private long baseStat;

    @Column(name="effort")
    private long effort;

    @Column(name="name")
    private String name;

    public Stat() {
    }

    public Stat(long id, long baseStat, long effort, String name) {
        this.id = id;
        this.baseStat = baseStat;
        this.effort = effort;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBaseStat() {
        return this.baseStat;
    }

    public void setBaseStat(long baseStat) {
        this.baseStat = baseStat;
    }

    public long getEffort() {
        return this.effort;
    }

    public void setEffort(long effort) {
        this.effort = effort;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stat id(long id) {
        setId(id);
        return this;
    }

    public Stat baseStat(long baseStat) {
        setBaseStat(baseStat);
        return this;
    }

    public Stat effort(long effort) {
        setEffort(effort);
        return this;
    }

    public Stat name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Stat)) {
            return false;
        }
        Stat stat = (Stat) o;
        return id == stat.id && baseStat == stat.baseStat && effort == stat.effort && Objects.equals(name, stat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, baseStat, effort, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", baseStat='" + getBaseStat() + "'" +
            ", effort='" + getEffort() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
