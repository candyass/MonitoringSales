package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Akun;

import java.util.List;

/**
 * Created by sigit on 24/09/2018.
 */

public class DaftarSalesFragmentViewModel extends AndroidViewModel {

    private SalesRepository salesRepository;

    public DaftarSalesFragmentViewModel(@NonNull Application application) {
        super(application);
        salesRepository = SalesRepository.getInstance(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        salesRepository = null;
    }

    public LiveData<List<Akun>> getAllAkun() {
        return salesRepository.getAllAkun();
    }
}
