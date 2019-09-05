package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.model.QueryTotalBungkus;
import com.sps.monitoringsales.model.QueryTotalHadiah;

import java.util.List;

/**
 * Created by sigit on 26/07/2018.
 */

public class MonitoringViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public MonitoringViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public LiveData<List<QueryTotalBungkus>> getQueryTotalBungkus(String idAkun) {
        return repository.getQueryTotalBungkus(idAkun);
    }

    public LiveData<List<QueryTotalHadiah>> getQueryTotalHadiah(String idAkun) {
        return repository.getQueryTotalHadiah(idAkun);
    }
}
