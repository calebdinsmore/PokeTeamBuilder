package com.swag.calebdinsmore.pokemonmasterteambuilder;

/**
 * calebdinsmore made this kickin' app on 3/22/15.
 */
public class PokemonEntry {
    private String name;
    private String resource_uri;

    public PokemonEntry(String name, String resource_uri) {
        this.name = name;
        this.resource_uri = resource_uri;
    }

    //region GETS AND SETS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

}
