package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by sigit on 17/06/2018.
 */

@Entity
public class Hadiah {

    public static int ID_GELAS = 1;
    public static int ID_PIRING = 2;
    public static int ID_MANGKOK = 3;


    @PrimaryKey
    private int idHadiah;
    private String namaHadiah;


    public static Hadiah[] getDaftarHadiah() {
        Hadiah[] hadiahs = {
          new Hadiah(ID_GELAS, "Gelas"),
                new Hadiah(ID_PIRING, "Piring"), new Hadiah(ID_MANGKOK, "Mangkok")
        };
        return hadiahs;
    }

    public Hadiah(int idHadiah, String namaHadiah) {
        this.setIdHadiah(idHadiah);
        this.setNamaHadiah(namaHadiah);
    }


    public int getIdHadiah() {
        return idHadiah;
    }

    public void setIdHadiah(int idHadiah) {
        this.idHadiah = idHadiah;
    }

    public String getNamaHadiah() {
        return namaHadiah;
    }

    public void setNamaHadiah(String namaHadiah) {
        this.namaHadiah = namaHadiah;
    }
}
