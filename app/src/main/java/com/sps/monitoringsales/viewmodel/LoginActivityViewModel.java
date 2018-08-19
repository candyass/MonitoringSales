package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.util.MyLogger;

import java.util.concurrent.ExecutionException;

/**
 * Created by sigit on 21/06/2018.
 */

public class LoginActivityViewModel extends AndroidViewModel {

    SalesRepository repository;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }


    public Akun getAkunLogin(String salesID, String passwordSales) {
        Akun akun = null;
        try {
             akun = repository.getAkun(salesID,passwordSales);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return akun;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        MyLogger.logPesan("LoginActivityViewModel onCleared");
        repository = null;
    }
}
