package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.model.QueryPenukaranHadiah;

import java.util.List;

/**
 * Created by sigit on 19/06/2018.
 */

public class PenukaranHadiahFragmentViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public PenukaranHadiahFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }



    public LiveData<List<QueryPenukaranHadiah>> getAllPenukaranHadiah(String akunId) {
        return repository.loadPenukaranHadiah(akunId);
    }
}
