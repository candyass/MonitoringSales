package com.sps.monitoringsales;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.sps.monitoringsales.database.LocalDatabase;

/**
 * Created by sigit on 21/04/2018.
 */

public class MyApplication extends Application {

    private LocalDatabase localDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        localDatabase = Room.databaseBuilder(this, LocalDatabase.class, "MonitoringDB").build();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        localDatabase.close();
    }

    public LocalDatabase getLocalDatabase() {
        return localDatabase;
    }
}
