package com.sps.monitoringsales.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.graphics.Bitmap;

import com.sps.monitoringsales.database.entity.Hadiah;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;
import com.sps.monitoringsales.model.PenukaranBungkusQuery;
import com.sps.monitoringsales.model.QueryPenukaranBungkus;
import com.sps.monitoringsales.model.QueryPenukaranHadiah;
import com.sps.monitoringsales.model.QueryTotalHadiah;

import java.util.List;

/**
 * Created by sigit on 06/05/2018.
 */

@Dao
public abstract class PenukaranBungkusDAO {

    @Insert
    public abstract long savePenukaran(Penukaran penukaran);

    @Insert
    public abstract void savePenukaranBungkus(List<PenukaranBungkus> list);

    @Insert
    public abstract void saveHadiah(Hadiah... hadiahs);

    @Insert
    protected abstract void savePenukaranHadiah(List<PenukaranHadiah> listPenukaranHadiah);

    @Transaction
    public void savePenukaranHadiah(Penukaran penukaran, List<PenukaranHadiah> listPenukaranHadiah) {
        savePenukaranHadiah(listPenukaranHadiah);
        penukaran.setDitukar(true);
        updatePenukaran(penukaran);

    }

    @Update
    public abstract void updatePenukaran(Penukaran penukaran);



    @Query("SELECT * FROM Penukaran WHERE id = :idPenukaran")
    public abstract LiveData<Penukaran> getPenukaran(int idPenukaran);

    @Query("SELECT Penukaran.id, foto, namaOutlet, tanggalPenukaran FROM Penukaran JOIN Outlet " +
            "ON Penukaran.idOutlet = Outlet.id WHERE ditukar = 1")
    public abstract LiveData<List<QueryPenukaranHadiah>> loadPenukaranHadiah();

    @Query("UPDATE Outlet SET jenisOutlet = 'RO' WHERE id = :idOutlet")
    public abstract void updateOutletKeRO(int idOutlet);

    @Query("UPDATE Penukaran SET tandaTangan = :TTD WHERE id = :idPenukaran")
    public abstract void saveTandaTangan(int idPenukaran, Bitmap TTD);

    @Transaction
    public void insertAllPenukaranAndBungkus(Penukaran penukaran, List<PenukaranBungkus> list) {
        long id = savePenukaran(penukaran);
        updateOutletKeRO(penukaran.getIdOutlet());
        for(PenukaranBungkus p : list) {
            p.setIdPenukaran((int)id);
        }
        savePenukaranBungkus(list);
    }

    @Query("SELECT Outlet.id, foto, namaOutlet, idPenukaran, tandaTangan, tanggalPenukaran, ditukar, pb.totalBungkus FROM (SELECT idPenukaran, SUM(jumlahBungkus) " +
            "AS totalBungkus FROM PenukaranBungkus " +
            "GROUP BY idPenukaran) " +
            "AS pb LEFT OUTER JOIN Penukaran ON pb.idPenukaran = Penukaran.id " +
            "JOIN outlet ON Penukaran.idOutlet = Outlet.id")
    public abstract LiveData<List<PenukaranBungkusQuery>> getPenukaran();

    @Query("SELECT namaHadiah, ph.totalHadiah FROM " +
            "Hadiah LEFT OUTER JOIN (SELECT hadiahId, SUM(jumlahHadiah) AS " +
            "totalHadiah FROM PenukaranHadiah GROUP BY hadiahId) AS ph " +
            "ON idHadiah = hadiahId")
    public abstract LiveData<List<QueryTotalHadiah>> getQueryTotalHadiah();




}
