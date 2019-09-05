package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Outlet;

import java.util.List;

/**
 * Created by sigit on 21/04/2018.
 */

public class OutletFragmentViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public OutletFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public LiveData<List<Outlet>> getListOutlet(String idAkun) {
        return repository.getAllOutlet(idAkun);
    }
}
