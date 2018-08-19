package com.sps.monitoringsales.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sps.monitoringsales.database.entity.Sales;

/**
 * Created by sigit on 21/06/2018.
 */

@Dao
public interface SalesDAO {

    @Insert
    void saveSales(Sales sales);

    @Query("SELECT * FROM Sales LIMIT 1")
    Sales getSales();
}
