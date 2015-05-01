package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class AdvancedSearchFragment extends Fragment {
    TextView name, type, number;
    EditText name_field, type_field, number_field;
    Spinner spinner;

    public AdvancedSearchFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_advanced_search, container, false);
        name = (TextView) getActivity().findViewById(R.id.name);
        type = (TextView) getActivity().findViewById(R.id.type);
        number = (TextView) getActivity().findViewById(R.id.number);

        name_field = (EditText) getActivity().findViewById(R.id.name_field);
        type_field = (EditText) getActivity().findViewById(R.id.type_field);
        number_field = (EditText) getActivity().findViewById(R.id.number_field);

        spinner = (Spinner) getActivity().findViewById(R.id.gen_spinner);

        return rootView;
    }
}