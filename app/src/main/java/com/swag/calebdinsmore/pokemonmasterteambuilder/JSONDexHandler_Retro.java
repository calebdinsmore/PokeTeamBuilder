package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * calebdinsmore made this kickin' app on 3/19/15.
 */
public class JSONDexHandler_Retro {
    private final String TAG = "JSONDexHandler_Retro";
    public static PokeApi pokeApi;
    private static Pokedex myPokedex;

    public JSONDexHandler_Retro() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.pokeapi.co")
                .build();

        pokeApi = restAdapter.create(PokeApi.class);
    }

    public void fetchPokedex(final Context applicationContext) {
        pokeApi.getPokedex(new Callback<Pokedex>() {

            @Override
            public void success(Pokedex pokedex, Response response) {
                myPokedex = pokedex;
                Gson gson = new Gson();

                String pokedexJson = gson.toJson(pokedex);

                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(Main_Screen.JSON, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Pokedex", pokedexJson);

                editor.apply();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }

    public Pokedex getMyPokedex() {
        return myPokedex;
    }

    public static void setMyPokedex(Pokedex myPokedex) {
        JSONDexHandler_Retro.myPokedex = myPokedex;
    }
}
