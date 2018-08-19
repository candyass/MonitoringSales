package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
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


    public LiveData<List<PenukaranBungkusQuery>> getAllPenukranBungkus() {
        return repository.getAllPenukranBungkus();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
