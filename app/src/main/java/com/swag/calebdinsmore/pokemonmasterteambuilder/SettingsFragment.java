package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class SettingsFragment extends Fragment {
    private static final String TAG = "SettingsFragment";
    private static final String PREFS_NAME = "Settings";
    private ToggleButton suggestionsToggle;
    public static boolean pokeSuggestions;
    private SharedPreferences settings;

    public SettingsFragment(){
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        suggestionsToggle = (ToggleButton) rootView.findViewById(R.id.suggestionsToggle);
        setListeners();

        settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        pokeSuggestions = settings.getBoolean("Suggestions", true);
        suggestionsToggle.setChecked(pokeSuggestions);

        return rootView;
    }

    private void setListeners() {
        //PokemonEntry suggestions toggle button listener
        suggestionsToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokeSuggestions = !pokeSuggestions;
                suggestionsToggle.setChecked(pokeSuggestions);
            }
        });
    }

    @Override
     public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("Suggestions", pokeSuggestions);

        editor.commit();
    }

}