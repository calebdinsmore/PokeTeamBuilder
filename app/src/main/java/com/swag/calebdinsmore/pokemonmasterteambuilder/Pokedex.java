package com.swag.calebdinsmore.pokemonmasterteambuilder;

import java.util.ArrayList;

/**
 * calebdinsmore made this kickin' app on 4/7/15.
 */
public class Pokedex {
    private String name;
    private ArrayList<PokemonEntry> pokemon;

    public Pokedex(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PokemonEntry> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<PokemonEntry> pokemon) {
        this.pokemon = pokemon;
    }
}
