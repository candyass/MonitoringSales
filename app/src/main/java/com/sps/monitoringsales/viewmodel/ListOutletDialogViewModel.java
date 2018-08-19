package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Outlet;

import java.util.List;

/**
 * Created by sigit on 27/04/2018.
 */

public class ListOutletDialogViewModel extends AndroidViewModel {

    private LiveData<List<Outlet>> mListOutlet;

    public ListOutletDialogViewModel(@NonNull Application application) {
        super(application);
        mListOutlet = SalesRepository.getInstance(application).getAllOutlet();
    }

    public LiveData<List<Outlet>> getAllOutlet() {
        return mListOutlet;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mListOutlet = null;
    }
}
