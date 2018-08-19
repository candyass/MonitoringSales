package com.sps.monitoringsales;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.graphics.Bitmap;

import com.sps.monitoringsales.database.LocalDatabase;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.database.entity.Bungkus;
import com.sps.monitoringsales.database.entity.Hadiah;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;
import com.sps.monitoringsales.database.entity.Sales;
import com.sps.monitoringsales.model.PenukaranBungkusQuery;
import com.sps.monitoringsales.model.QueryPenukaranHadiah;
import com.sps.monitoringsales.model.QueryTotalBungkus;
import com.sps.monitoringsales.model.QueryTotalHadiah;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by sigit on 21/04/2018.
 */

public class SalesRepository {

    private static SalesRepository singleton;

    private LocalDatabase localDatabase;
    private Executor executor;
    private Context context;

    private Akun mAkun;

    public static synchronized SalesRepository getInstance(Context context) {
        if(singleton == null) {
            singleton = new SalesRepository(context);
        }
        return singleton;
    }


    private SalesRepository(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        localDatabase = app.getLocalDatabase();
        this.context = context;
        executor = Executors.newSingleThreadExecutor();
        boolean isPersisted = PreferenceUtils.getInstance(context).getBoolean();
        if(!isPersisted) {
            saveDaftarBungkus(Bungkus.getDaftarBungkus());
            saveDaftarHadiah(Hadiah.getDaftarHadiah());
            List<Akun> listAkun = new ArrayList<>();
            listAkun.add(new Akun("270031","serang","Riazky",Akun.LOGIN_SALES));
            listAkun.add(new Akun("430031","serang","Sigit",Akun.LOGIN_ADMIN));
            saveAkun(listAkun);
            PreferenceUtils.getInstance(context).saveBoolean(true);
        }

    }

    private void saveDaftarBungkus(Bungkus... bungkuses) {
        Runnable r =() -> {
            localDatabase.getBungkusDAO().saveBungkus(bungkuses);
        };
        executor.execute(r);
    }

    private void saveDaftarHadiah(Hadiah... hadiahs) {
        Runnable r = () -> {
          localDatabase.getPenukaranBungkusDAO().saveHadiah(hadiahs);
        };
        executor.execute(r);
    }


    private void saveAkun(List<Akun> list) {
        Runnable r = () -> {
          localDatabase.getAkunDAO().insertAkun(list);
        };
        executor.execute(r);
    }


    public LiveData<List<Outlet>> getAllOutlet() {
        return localDatabase.getOutletDAO().getAllOutlet();
    }

    public LiveData<List<Bungkus>> getAllBungkus() {
        return localDatabase.getBungkusDAO().getAllBungkus();
    }

    public LiveData<Outlet> getOutlet(int idOutlet) {
        return localDatabase.getOutletDAO().getOutlet(idOutlet);
    }

    public LiveData<Bungkus> getBungkus(int bungkusId) {
        return localDatabase.getBungkusDAO().getBungkus(bungkusId);
    }

    public long saveOutlet(Outlet outlet) {
        Callable<Long> callable = () -> {
            outlet.setIdAkun(mAkun.getAkunId());
            return localDatabase.getOutletDAO().insertOutlet(outlet);
        };
        FutureTask<Long> task = new FutureTask<>(callable);
        executor.execute(task);
        try {
            return task.get();
        } catch (InterruptedException e) {
            return -1;
        } catch (ExecutionException e) {
            return -1;
        }
    }

    public void simpanPenukaranBungkusDipilih(Penukaran penukaran, List<PenukaranBungkus> list) {
        Runnable r = () -> {
            localDatabase.getPenukaranBungkusDAO().insertAllPenukaranAndBungkus(penukaran, list);
        };
        executor.execute(r);
    }

    public LiveData<List<PenukaranBungkusQuery>> getAllPenukranBungkus() {
        return localDatabase.getPenukaranBungkusDAO().getPenukaran();
    }

    public LiveData<Penukaran> getPenukaran(int idPenukaran) {
        return localDatabase.getPenukaranBungkusDAO().getPenukaran(idPenukaran);
    }

    public void savePenukaranHadiah(Penukaran penukaran, List<PenukaranHadiah> listPenukaranHadiah) {
        Runnable r = () -> {
          localDatabase.getPenukaranBungkusDAO().savePenukaranHadiah(penukaran, listPenukaranHadiah);
        };
        executor.execute(r);
    }

    public LiveData<List<QueryPenukaranHadiah>> loadPenukaranHadiah() {
        return  localDatabase.getPenukaranBungkusDAO().loadPenukaranHadiah();
    }



    public Akun getAkun(String akunId, String password) throws ExecutionException, InterruptedException {
        Callable<Akun> callable = () -> localDatabase.getAkunDAO().getAkun(akunId, password);
        FutureTask<Akun> task = new FutureTask<Akun>(callable);
        executor.execute(task);
        mAkun = task.get();
        return mAkun;
    }

    public Akun getCurrentAkun() {
        return mAkun;
    }

    public void simpanTandaTangan(int idPenukaran, Bitmap ttd) {
        Runnable r = () -> localDatabase.getPenukaranBungkusDAO().saveTandaTangan(idPenukaran, ttd);
        executor.execute(r);
    }

    public LiveData<List<QueryTotalBungkus>> getQueryTotalBungkus() {
        return localDatabase.getBungkusDAO().getQueryTotalBungkus();
    }

    public LiveData<List<QueryTotalHadiah>> getQueryTotalHadiah() {
        return localDatabase.getPenukaranBungkusDAO().getQueryTotalHadiah();
    }





    public Context getContext() {
        return context;
    }
}
