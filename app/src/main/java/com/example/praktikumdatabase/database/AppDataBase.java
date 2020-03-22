package com.example.praktikumdatabase.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataDiri.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DataDiriDAO dao();
    private static AppDataBase appDataBase;

    public static AppDataBase iniDb(Context context){
        if(appDataBase == null)
            appDataBase = Room.databaseBuilder(context,AppDataBase.class,"dbUser").allowMainThreadQueries().build();
        return appDataBase;
    }
    public static void destroyInstance(){appDataBase = null;}

}
