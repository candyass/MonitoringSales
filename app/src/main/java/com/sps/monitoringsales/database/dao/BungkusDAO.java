package com.sps.monitoringsales.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sps.monitoringsales.database.entity.Bungkus;
import com.sps.monitoringsales.model.QueryTotalBungkus;

import java.util.List;

/**
 * Created by sigit on 21/04/2018.
 */
@Dao
public interface BungkusDAO {

    @Insert
    public void saveBungkus(Bungkus... bungkus);

    @Update
    public void updateBungkus(Bungkus bungkus);

    @Query("SELECT * FROM Bungkus")
    public LiveData<List<Bungkus>> getAllBungkus();

    @Query("SELECT * FROM Bungkus WHERE idBungkus = :idBungkus")
    public LiveData<Bungkus> getBungkus(int idBungkus);

    @Query("SELECT namaBungkus, pb.totalBungkus FROM " +
            "Bungkus LEFT OUTER JOIN (SELECT idBungkus, SUM(jumlahBungkus) as totalBungkus " +
            "FROM PenukaranBungkus GROUP BY idBungkus) AS pb " +
            "ON Bungkus.idBungkus = pb.idBungkus")
    public LiveData<List<QueryTotalBungkus>> getQueryTotalBungkus();
}
