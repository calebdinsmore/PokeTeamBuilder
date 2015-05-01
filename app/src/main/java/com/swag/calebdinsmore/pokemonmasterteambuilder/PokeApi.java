package com.swag.calebdinsmore.pokemonmasterteambuilder;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * calebdinsmore made this kickin' app on 4/7/15.
 */
public interface PokeApi {
    @GET("/api/v1/pokedex/1")
    void getPokedex(Callback<Pokedex> callback);

    @GET("/{resource}")
    void getPokemon(@Path("resource") String resource, Callback<Pokemon> callback);
}
