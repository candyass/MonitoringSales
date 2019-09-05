package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.util.MyLogger;

import java.util.concurrent.ExecutionException;

public class LoginOutletActivityViewModel extends AndroidViewModel {

    SalesRepository repository;

    public LoginOutletActivityViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }


    public Outlet getOutletLogin(String namaPemilik, String noTelepon) {
        Outlet outlet = null;
        try {
            outlet = repository.getOutlet(namaPemilik, noTelepon);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return outlet;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        MyLogger.logPesan("LoginOutletActivityViewModel onCleared");
        repository = null;
    }
}
