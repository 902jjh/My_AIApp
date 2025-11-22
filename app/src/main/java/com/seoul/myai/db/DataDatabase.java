package com.seoul.myai.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.seoul.myai.DataItem;


@Database(
        entities = {DataItem.class},
        version = 1,
        exportSchema = false
)
public abstract class DataDatabase extends RoomDatabase {

    public abstract DataItemDao dataItemDao();

    private static DataDatabase INSTANCE;

    public static DataDatabase getINSTANCE(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DataDatabase.class,
                    "data_database")
                    .build();
        }
            return INSTANCE;
    }
}

