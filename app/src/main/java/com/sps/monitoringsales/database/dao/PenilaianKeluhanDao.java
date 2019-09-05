package com.sps.monitoringsales.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.graphics.Bitmap;

import com.sps.monitoringsales.database.entity.KeluhanSaran;
import com.sps.monitoringsales.database.entity.Penilaian;
import com.sps.monitoringsales.model.KeluhanSaranQuery;
import com.sps.monitoringsales.model.PenilaianKeluhanQuery;
import com.sps.monitoringsales.model.PenukaranBungkusQuery;

import java.util.List;

@Dao
public abstract class PenilaianKeluhanDao {


    @Insert
    public abstract long savePenilaian(Penilaian penilaian);

    @Insert
    public abstract void saveKeluhanSaran(List<KeluhanSaran> list);

    @Update
    public abstract void updatePenilaian(Penilaian penilaian);

    @Query("SELECT * FROM Penilaian WHERE id = :idPenilaian")
    public abstract LiveData<Penilaian> getPenilaian(int idPenilaian);

    @Query("UPDATE Outlet SET jenisOutlet = 'RO' WHERE id = :idOutlet")
    public abstract void updateOutletKeRO(int idOutlet);

    @Query("UPDATE Penilaian SET tandaTangan = :TTD WHERE id = :idPenilaian")
    public abstract void saveTandaTangan(int idPenilaian, Bitmap TTD);

    @Transaction
    public void insertAllPenukaranAndBungkus(Penilaian penilaian, List<KeluhanSaran> list) {
        long id = savePenilaian(penilaian);
        updateOutletKeRO(penilaian.getIdOutlet());
        for(KeluhanSaran p : list) {
            p.setIdKeluhanSaran((int)id);
        }
        saveKeluhanSaran(list);
    }


    @Query("SELECT Outlet.id, foto, namaOutlet, idKeluhanSaran, tandaTangan, tanggalPenilaian, isRating, pb.totalBungkus FROM (SELECT COUNT(idKeluhanSaran) as totalBungkus, idKeluhanSaran " +
            " FROM KeluhanSaran " +
            "GROUP BY idKeluhanSaran) " +
            "AS pb LEFT OUTER JOIN Penilaian ON pb.idKeluhanSaran = Penilaian.id " +
            "JOIN outlet ON Penilaian.idOutlet = Outlet.id WHERE Outlet.idAkun =:akunId")
    public abstract LiveData<List<PenilaianKeluhanQuery>> getPenilaianKeluhan(String akunId);


    @Query("SELECT Outlet.id, foto, namaOutlet, idKeluhanSaran, tandaTangan, tanggalPenilaian, isRating, pb.totalBungkus FROM (SELECT COUNT(idKeluhanSaran) as totalBungkus, idKeluhanSaran " +
            " FROM KeluhanSaran " +
            "GROUP BY idKeluhanSaran) " +
            "AS pb LEFT OUTER JOIN Penilaian ON pb.idKeluhanSaran = Penilaian.id " +
            "JOIN outlet ON Penilaian.idOutlet = Outlet.id WHERE Outlet.id =:id")
    public abstract LiveData<List<PenilaianKeluhanQuery>> getPenilaianKeluhanOutlet(int id);

    @Query("SELECT idKeluhanSaran, Bungkus.idBungkus, rating, Bungkus.namaBungkus FROM KeluhanSaran JOIN Bungkus " +
            "ON KeluhanSaran.idBungkus = Bungkus.idBungkus WHERE idKeluhanSaran =:id")
    public abstract LiveData<List<KeluhanSaranQuery>> getKeluhanSaranQuery(int id);

    @Query("UPDATE KeluhanSaran SET rating = 10 WHERE idKeluhanSaran = :id ")
    public abstract void updateRating(int id);


    @Query("UPDATE Penilaian SET isRating = :status WHERE id = :id")
    public abstract void updateStatusRating(int id, boolean status);

    @Transaction
    public void updateFullStatus(int id, boolean rating) {
        updateRating(id);
        updateStatusRating(id, rating);
    }

}
