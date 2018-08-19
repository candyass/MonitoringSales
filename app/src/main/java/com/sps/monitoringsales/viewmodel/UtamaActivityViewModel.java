package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.database.entity.Sales;

/**
 * Created by sigit on 21/06/2018.
 */

public class UtamaActivityViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public UtamaActivityViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }

    public Akun getAkun() {
        return repository.getCurrentAkun();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
