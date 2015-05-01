package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.util.Log;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * calebdinsmore made this legit class on 4/9/15.
 */
public class Pokemon {
    private String name;
    private String resource_uri;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer sp_atk;
    private Integer sp_def;
    private Integer speed;

    private ArrayList<TypeResource> types;

    public Pokemon(String name, String resource_uri) {
        this.name = name;
        this.resource_uri = resource_uri;
    }

    //region GETS AND SETS
    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSp_atk() {
        return sp_atk;
    }

    public void setSp_atk(Integer sp_atk) {
        this.sp_atk = sp_atk;
    }

    public Integer getSp_def() {
        return sp_def;
    }

    public void setSp_def(Integer sp_def) {
        this.sp_def = sp_def;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public ArrayList<TypeResource> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypeResource> types) {
        this.types = types;
    }

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
//endregion

    public int calculateTotal() {
        return attack + defense + sp_atk + sp_def + hp + speed;
    }

    public void setStatsFromResource() {
        JSONDexHandler_Retro.pokeApi.getPokemon(resource_uri, new Callback<Pokemon>() {
            @Override
            public void success(Pokemon pokemon, Response response) {
                hp = pokemon.getHp();
                attack = pokemon.getAttack();
                defense = pokemon.getDefense();
                sp_atk = pokemon.getSp_atk();
                sp_def = pokemon.getSp_def();
                speed = pokemon.getSpeed();
                types = pokemon.getTypes();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    public class TypeResource {
        String name;
        String resource_uri;

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
        //endregion
    }
}
