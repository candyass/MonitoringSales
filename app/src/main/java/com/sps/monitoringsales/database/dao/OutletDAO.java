package com.sps.monitoringsales.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sps.monitoringsales.database.entity.Outlet;

import java.util.List;

/**
 * Created by sigit on 21/04/2018.
 */
@Dao
public  interface OutletDAO {

    @Insert
    public long insertOutlet(Outlet outlet);

    @Delete
    public void deleteOutlet(Outlet outlet);

    @Update
    public void updateOutlet(Outlet outlet);

    @Query("SELECT * FROM outlet WHERE idAkun =:akunId ")
    public LiveData<List<Outlet>> getAllOutlet(String akunId);


    @Query("SELECT * FROM outlet WHERE id = :id")
    public LiveData<Outlet> getOutlet(int id);

    @Query("SELECT * FROM outlet WHERE namaPemilik =:namaPemilik AND noTelepon =:noTelepon")
    public Outlet getOutlet(String namaPemilik, String noTelepon);
}
