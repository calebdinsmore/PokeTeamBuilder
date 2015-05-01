package com.swag.calebdinsmore.pokemonmasterteambuilder;

import java.util.ArrayList;

/**
 * calebdinsmore made this legit class on 4/9/15.
 */
public class Team {
    private static ArrayList<Pokemon> teamList;

    public static ArrayList<Pokemon> getTeamList() {
        return teamList;
    }

    public static void setTeamList(ArrayList<Pokemon> teamList) {
        Team.teamList = teamList;
    }
}
