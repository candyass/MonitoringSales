package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.sps.monitoringsales.R;

/**
 * Created by sigit on 05/04/2018.
 */

@Entity
public class Bungkus {

    @PrimaryKey(autoGenerate = true)
    private int idBungkus;
    private String namaBungkus;
    private int gambarBungkus;

    @Ignore
    public Bungkus() {

    }

    public Bungkus(int idBungkus, String namaBungkus, int gambarBungkus) {
        this.idBungkus = idBungkus;
        this.namaBungkus = namaBungkus;
        this.gambarBungkus = gambarBungkus;
    }

    @Ignore
    public Bungkus(String namaBungkus, int gambarBungkus) {
        this.namaBungkus = namaBungkus;
        this.gambarBungkus = gambarBungkus;
    }


    public int getIdBungkus() {
        return idBungkus;
    }

    public void setIdBungkus(int idBungkus) {
        this.idBungkus = idBungkus;
    }

    public String getNamaBungkus() {
        return namaBungkus;
    }

    public void setNamaBungkus(String namaBungkus) {
        this.namaBungkus = namaBungkus;
    }

    public int getGambarBungkus() {
        return gambarBungkus;
    }

    public void setGambarBungkus(int gambarBungkus) {
        this.gambarBungkus = gambarBungkus;
    }


    public static Bungkus[] getDaftarBungkus() {
        Bungkus[] bungkuses =
                {
                        new Bungkus("Saos 1", R.drawable.product_1),
                        new Bungkus("Saos 2", R.drawable.product_2),
                        new Bungkus("Saos 3", R.drawable.product_3),
                        new Bungkus("Saos 4", R.drawable.product_4),
                        new Bungkus("Saos 5", R.drawable.product_5),
                        new Bungkus("Saos 6", R.drawable.product_6)
                };
        return bungkuses;
    }
}
