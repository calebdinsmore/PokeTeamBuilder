package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TeamFragment extends Fragment {
    ArrayList<String> teamList;
    ListView teamListView;
    ArrayAdapter<String> teamAdapter;

    public TeamFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_team, container, false);
        teamListView = (ListView) rootView.findViewById(R.id.teamList);
        teamList = new ArrayList<String>();
        teamList.add("Charmander");
        teamList.add("Arceus");
        teamAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.basic_list_item, teamList);
        teamListView.setAdapter(teamAdapter);


        return rootView;
    }
}
