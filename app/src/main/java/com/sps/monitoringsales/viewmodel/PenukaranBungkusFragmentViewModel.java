package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.model.PenilaianKeluhanQuery;
import com.sps.monitoringsales.model.PenukaranBungkusQuery;

import java.util.List;

/**
 * Created by sigit on 27/05/2018.
 */

public class PenukaranBungkusFragmentViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public PenukaranBungkusFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }


    public LiveData<List<PenukaranBungkusQuery>> getAllPenukranBungkus(String idAkun) {
        return repository.getAllPenukranBungkus(idAkun);
    }

    public LiveData<List<PenilaianKeluhanQuery>> getAllPenilaianKeluhan(String idAkun) {
        return repository.getAllPenilaianKeluhan(idAkun);
    }

    public LiveData<List<PenilaianKeluhanQuery>> getPenilaianKeluhanOutlet(int id) {
        return repository.getPenilaianKeluhanOutlet(id);
    }

    public Outlet getCurrentOutlet() {
        return repository.getCurrentOutlet();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
