package com.sps.monitoringsales.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.sps.monitoringsales.database.dao.AkunDAO;
import com.sps.monitoringsales.database.dao.BungkusDAO;
import com.sps.monitoringsales.database.dao.OutletDAO;
import com.sps.monitoringsales.database.dao.PenukaranBungkusDAO;
import com.sps.monitoringsales.database.dao.SalesDAO;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.database.entity.BitmapConverter;
import com.sps.monitoringsales.database.entity.Bungkus;
import com.sps.monitoringsales.database.entity.DateConverter;
import com.sps.monitoringsales.database.entity.Hadiah;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;
import com.sps.monitoringsales.database.entity.Sales;

/**
 * Created by sigit on 21/04/2018.
 */
@Database(version = 1, entities = {Outlet.class, Bungkus.class, Hadiah.class, Sales.class, Penukaran.class, PenukaranBungkus.class, PenukaranHadiah.class, Akun.class}, exportSchema = false)
@TypeConverters({DateConverter.class, BitmapConverter.class})
public abstract class LocalDatabase extends RoomDatabase {


    public abstract OutletDAO getOutletDAO();

    public abstract BungkusDAO getBungkusDAO();

    public abstract AkunDAO getAkunDAO();

    public abstract PenukaranBungkusDAO getPenukaranBungkusDAO();
}
