package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.model.KeluhanSaranQuery;

import java.util.List;

public class InputRatingActivityViewModel extends AndroidViewModel {

    private SalesRepository repository;

    public InputRatingActivityViewModel(@NonNull Application application) {
        super(application);
        repository =  SalesRepository.getInstance(application);
    }

    public LiveData<List<KeluhanSaranQuery>> getKeluhanSaranQuery(int id) {
        return repository.getKeluhanSaranQuery(id);
    }

    public void updateFullStatus(int idPenilaian) {
         repository.updateRating(idPenilaian);
    }
}
