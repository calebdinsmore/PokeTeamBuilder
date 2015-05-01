package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SelectedPokemonFragment extends Fragment {
    private TextView name,
    hp,
    attack,
    defense,
    spatk,
    spdef,
    speed,
    total;
    ImageView sprite;
    String sprite_res;

    public SelectedPokemonFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_selected_pokemon, container, false);

        name = (TextView) rootView.findViewById(R.id.name);
        hp = (TextView) rootView.findViewById(R.id.hp);
        attack = (TextView) rootView.findViewById(R.id.attack);
        defense = (TextView) rootView.findViewById(R.id.defense);
        spatk = (TextView) rootView.findViewById(R.id.spatk);
        spdef = (TextView) rootView.findViewById(R.id.spdef);
        speed = (TextView) rootView.findViewById(R.id.speed);
        total = (TextView) rootView.findViewById(R.id.total);

        sprite = (ImageView) rootView.findViewById(R.id.sprite);

        setTextFromBundle();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Sprite sprite1 = JSONDexHandler_Retro.pokeApi.getSprite(sprite_res);
                Picasso.with(getActivity().getApplicationContext()).load("http://i.imgur.com/DvpvklR.png").into(sprite);
            }
        });
        thread.start();


        return rootView;
    }

    public void setTextFromBundle() {
        Bundle args = getArguments();
        attack.setText(Integer.toString(args.getInt("atk")));
        defense.setText(Integer.toString(args.getInt("def")));
        spatk.setText(Integer.toString(args.getInt("spatk")));
        spdef.setText(Integer.toString(args.getInt("spdef")));
        speed.setText(Integer.toString(args.getInt("speed")));
        total.setText(Integer.toString(args.getInt("total")));
        hp.setText(Integer.toString(args.getInt("hp")));
        name.setText(args.getString("name"));

        sprite_res = args.getString("sprite_res");
    }
}
