package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Outlet;

/**
 * Created by sigit on 24/04/2018.
 */

public class DetailOutletActivityViewModel extends AndroidViewModel {

    private SalesRepository repository;
    public DetailOutletActivityViewModel(@NonNull Application application) {
        super(application);
         repository = SalesRepository.getInstance(application);
    }

    public LiveData<Outlet> getOutlet(int id) {
        return repository.getOutlet(id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

}
