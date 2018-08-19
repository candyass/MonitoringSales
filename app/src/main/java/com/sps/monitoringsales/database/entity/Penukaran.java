package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by sigit on 05/05/2018.
 */

@Entity(
        foreignKeys = @ForeignKey(entity = Outlet.class, parentColumns = "id", childColumns = "idOutlet"),
        indices = @Index("idOutlet"))
public class Penukaran {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idOutlet;
    private Date tanggalPenukaran;
    private boolean ditukar;
    @Nullable
    private Bitmap tandaTangan;

    @Ignore
    public Penukaran() {

    }

    public Penukaran(int id, int idOutlet, Date tanggalPenukaran, boolean ditukar, Bitmap tandaTangan) {
        this.setId(id);
        this.setIdOutlet(idOutlet);
        this.setTanggalPenukaran(tanggalPenukaran);
        this.setDitukar(ditukar);
        this.setTandaTangan(tandaTangan);
    }

    @Ignore
    public Penukaran(int idOutlet) {
        this.setIdOutlet(idOutlet);
        this.tanggalPenukaran = new Date();
        ditukar = false;
    }




    public int getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(int idOutlet) {
        this.idOutlet = idOutlet;
    }

    public Date getTanggalPenukaran() {
        return tanggalPenukaran;
    }

    public void setTanggalPenukaran(Date tanggalPenukaran) {
        this.tanggalPenukaran = tanggalPenukaran;
    }

    public boolean isDitukar() {
        return ditukar;
    }

    public void setDitukar(boolean ditukar) {
        this.ditukar = ditukar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTandaTangan(Bitmap tandaTangan) {
        this.tandaTangan = tandaTangan;
    }

    public Bitmap getTandaTangan() {
        return tandaTangan;
    }

}
