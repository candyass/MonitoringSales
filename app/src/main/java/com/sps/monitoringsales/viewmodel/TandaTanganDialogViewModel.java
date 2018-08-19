package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;

/**
 * Created by sigit on 23/06/2018.
 */

public class TandaTanganDialogViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public TandaTanganDialogViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }


    public void saveTandaTangan(int idPenukaran, Bitmap ttd) {
        repository.simpanTandaTangan(idPenukaran, ttd);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
