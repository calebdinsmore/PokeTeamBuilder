package com.swag.calebdinsmore.pokemonmasterteambuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * calebdinsmore made this sick class on 4/7/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseOpenHelper";
    private static final String DBNAME = "pokestorage";
    public static final String TABLENAME = "pokemon";

    //region FIELDS
    private final String POKE_NAME = "name";
    private final String POKE_HP = "hp";
    private final String POKE_ATK = "attack";
    private final String POKE_SPATK = "sp_atk";
    private final String POKE_DEF = "defense";
    private final String POKE_SPDEF = "sp_def";
    private final String POKE_SPEED = "speed";
//endregion

    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POKEMON_TABLE = String.format("CREATE TABLE %s" + //Tablename
                " ( %s varchar(32), " + //Name
                "%s INTEGER, " +        //HP
                "%s INTEGER, " +        //Attack
                "%s INTEGER, " +        //Special Attack
                "%s INTEGER, " +        //Defense
                "%s INTEGER, " +        //Special Defense
                "%s INTEGER );",        //Speed
                TABLENAME, POKE_NAME, POKE_HP, POKE_ATK, POKE_SPATK, POKE_DEF, POKE_SPDEF, POKE_SPEED);

        db.execSQL(CREATE_POKEMON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
