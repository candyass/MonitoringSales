package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

/**
 * Created by sigit on 06/05/2018.
 */

@Entity(
        foreignKeys = {
        @ForeignKey(entity = Penukaran.class, parentColumns = "id", childColumns = "idPenukaran"),
        @ForeignKey(entity = Bungkus.class, parentColumns = "idBungkus", childColumns = "idBungkus")},
        primaryKeys = {"idPenukaran", "idBungkus"})
public class PenukaranBungkus {

    private int idPenukaran;
    private int idBungkus;
    private int jumlahBungkus;


    public PenukaranBungkus(int idPenukaran, int idBungkus, int jumlahBungkus) {
        this.setIdPenukaran(idPenukaran);
        this.setIdBungkus(idBungkus);
        this.setJumlahBungkus(jumlahBungkus);
    }

    @Ignore
    public PenukaranBungkus(int idBungkus, int jumlahBungkus) {
        this.setIdBungkus(idBungkus);
        this.setJumlahBungkus(jumlahBungkus);
    }


    public int getIdPenukaran() {
        return idPenukaran;
    }

    public void setIdPenukaran(int idPenukaran) {
        this.idPenukaran = idPenukaran;
    }

    public int getIdBungkus() {
        return idBungkus;
    }

    public void setIdBungkus(int idBungkus) {
        this.idBungkus = idBungkus;
    }

    public int getJumlahBungkus() {
        return jumlahBungkus;
    }

    public void setJumlahBungkus(int jumlahBungkus) {
        this.jumlahBungkus = jumlahBungkus;
    }
}
