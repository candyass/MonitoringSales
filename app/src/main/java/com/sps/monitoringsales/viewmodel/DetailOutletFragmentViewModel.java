package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Outlet;

public class DetailOutletFragmentViewModel extends AndroidViewModel {


    private SalesRepository salesRepository;

    public DetailOutletFragmentViewModel(@NonNull Application application) {
        super(application);
        salesRepository = SalesRepository.getInstance(application);
    }

    public Outlet getOutlet() {
        return salesRepository.getCurrentOutlet();
    }
}
