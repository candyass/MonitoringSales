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

    private LiveData<List<Outlet>> listOutlet;

    public OutletFragmentViewModel(@NonNull Application application) {
        super(application);
        SalesRepository repository = SalesRepository.getInstance(application);
        listOutlet = repository.getAllOutlet();
    }


    public LiveData<List<Outlet>> getListOutlet() {
        return listOutlet;
    }
}
