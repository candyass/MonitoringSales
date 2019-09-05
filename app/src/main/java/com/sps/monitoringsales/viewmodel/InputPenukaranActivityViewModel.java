package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.KeluhanSaran;
import com.sps.monitoringsales.database.entity.Penilaian;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.model.OutletEvent;

import java.util.List;

/**
 * Created by sigit on 05/05/2018.
 */

public class InputPenukaranActivityViewModel extends AndroidViewModel{

    private SalesRepository repository;
    private OutletEvent mOutletEvent;

    public InputPenukaranActivityViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }

    public void setOutletEvent(OutletEvent outletEvent) {
        mOutletEvent = outletEvent;
    }

    public OutletEvent getmOutletEvent() {
        return mOutletEvent;
    }

    public void simpanAllSelectedBungkus(Penukaran penukaran, List<PenukaranBungkus> list) {
        repository.simpanPenukaranBungkusDipilih(penukaran, list);
    }

    public void simpanAllPenilaianBungkus(Penilaian penilaian, List<KeluhanSaran> list) {
        repository.simpanPenilaianBungkus(penilaian, list);
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
        mOutletEvent = null;
    }
}
