package com.sps.monitoringsales.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sps.monitoringsales.database.entity.Akun;

import java.util.List;

/**
 * Created by sigit on 04/07/2018.
 */

@Dao
public interface AkunDAO {

    @Insert
    public void insertAkun(List<Akun> list);

    @Query("SELECT * FROM Akun WHERE akunId =:idAkun AND password =:passwordAkun")
    public Akun getAkun(String idAkun, String passwordAkun);

    @Query("SELECT * FROM Akun WHERE loginSebagai = 1")
    public LiveData<List<Akun>> getAllAkun();
}
