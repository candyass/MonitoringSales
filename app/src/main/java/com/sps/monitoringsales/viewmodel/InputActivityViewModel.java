package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.database.entity.Outlet;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by sigit on 15/04/2018.
 */

public class InputActivityViewModel extends AndroidViewModel {

    private Bitmap foto;
    private Outlet outlet;
    private SalesRepository repository;

    public InputActivityViewModel(@NonNull Application application) {
        super(application);
        repository =  SalesRepository.getInstance(application);
        this.outlet = new Outlet();
    }


    public Bitmap getFoto() {
        return foto;
    }



    public void simpanFoto(Uri uri) {
        Picasso pic = Picasso.with(repository.getContext());
        Runnable r = () -> {
            try {
                foto = pic.load(uri).get();
            } catch (IOException e) {
                MyLogger.logPesan(e.getMessage());
            }
        };
        new Thread(r).start();

    }

    public void reloadImage() {
        foto = null;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    public long simpanOutlet() {
        outlet.setFoto(foto);
        return repository.saveOutlet(outlet);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
