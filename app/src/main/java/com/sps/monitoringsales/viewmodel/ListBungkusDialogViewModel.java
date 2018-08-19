package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Bungkus;

import java.util.List;

/**
 * Created by sigit on 11/04/2018.
 */

public class ListBungkusDialogViewModel extends AndroidViewModel {

    private LiveData<List<Bungkus>> listLiveData;

    public ListBungkusDialogViewModel(@NonNull Application application) {
        super(application);
        listLiveData = SalesRepository.getInstance(application).getAllBungkus();
    }


    public LiveData<List<Bungkus>> getListBungkus() {
        return listLiveData;
    }
}
