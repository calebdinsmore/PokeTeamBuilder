package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.AdapterView.OnItemClickListener;


public class SearchableActivity extends ActionBarActivity {
    private final String TAG = "SearchableActivity";
    ListView resultListView;
    ArrayList<String> results;
    ArrayAdapter<String> resultsAdapter;
    HashMap<String, Pokemon> pokemonHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
//        TextView testResult = (TextView) findViewById(R.id.resultText);
        resultListView = (ListView) findViewById(R.id.search_list_view);
        results = new ArrayList<String>();
        resultsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.basic_list_item, results);
        resultListView.setAdapter(resultsAdapter);

        setupHashTable();

        //Get search query
        Intent intent = getIntent();
        if ( intent.ACTION_SEARCH.equals(intent.getAction()) ) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            processSearch(query);

            resultListView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    storeHashMap();
                    android.support.v4.app.Fragment pokeFrag = new SelectedPokemonFragment();
                    Bundle statsBundle = makeBundle(pokemonHashMap.get(results.get(position).toLowerCase()));
                    pokeFrag.setArguments(statsBundle);
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.container, pokeFrag).addToBackStack("PokeFrag").commit();
                }
            });
        }
    }

    private Bundle makeBundle(Pokemon pokemon) {
        Bundle makeBundle = new Bundle();
        makeBundle.putInt("atk", pokemon.getAttack());
        makeBundle.putInt("def", pokemon.getDefense());
        makeBundle.putInt("spatk", pokemon.getSp_atk());
        makeBundle.putInt("spdef", pokemon.getSp_def());
        makeBundle.putInt("speed", pokemon.getSpeed());
        makeBundle.putInt("hp", pokemon.getHp());
        makeBundle.putString("name", capitalize(pokemon.getName()));
        makeBundle.putInt("total", pokemon.calculateTotal());
        //makeBundle.putString("sprite_res", pokemon.getSprites().get(0).getResource_uri());
        return makeBundle;
    }

    private void setupHashTable() {
        SharedPreferences sharedPrefs = getSharedPreferences(Main_Screen.JSON, 0);
        Gson gson = new Gson();
        String pokeHashMapStr = sharedPrefs.getString("PokeHashMap", null);

        if ( pokeHashMapStr == null ) {
            pokemonHashMap = new HashMap<String, Pokemon>();
        } else {
            Type mapType = new TypeToken<HashMap<String, Pokemon>>() {
            }.getType();

            pokemonHashMap = gson.fromJson(pokeHashMapStr, mapType);
        }
    }

    private void storeHashMap() {
        Gson gson = new Gson();

        String pokeHashMapJson = gson.toJson(pokemonHashMap);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Main_Screen.JSON, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PokeHashMap", pokeHashMapJson);

        editor.apply();
    }

    private void processSearch(String query) {
        String queryLower = query.toLowerCase();
        boolean matchedAtLeastOne = false;
        try {
            //Get Pokedex array of PokemonEntry
            ArrayList<PokemonEntry> pokemonEntries = new JSONDexHandler_Retro().getMyPokedex().getPokemon();

            for ( int i = 0; i < pokemonEntries.size(); i++ ) {
                PokemonEntry currentPoke = pokemonEntries.get(i);
                //Regex to match if the query is found anywhere in the name
                if ( currentPoke.getName().toLowerCase().matches(".*" + query.toLowerCase() + ".*") ) {
                    results.add(capitalize(currentPoke.getName()));
                    Pokemon pokemon = new Pokemon(currentPoke.getName(), currentPoke.getResource_uri());
                    pokemon.setStatsFromResource();
                    pokemonHashMap.put(currentPoke.getName(), pokemon);
                    matchedAtLeastOne = true;
                }
            }

            if ( !matchedAtLeastOne ) {
                results.add("No matches");
                resultsAdapter.notifyDataSetChanged();
            } else {
                resultsAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searchable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
