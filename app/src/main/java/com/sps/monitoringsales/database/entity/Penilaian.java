package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.Date;

@Entity(
        foreignKeys = @ForeignKey(entity = Outlet.class, parentColumns = "id", childColumns = "idOutlet"),
        indices = @Index("idOutlet"))
public class Penilaian {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idOutlet;
    private Date tanggalPenilaian;
    @Nullable
    private Bitmap tandaTangan;
    private boolean isRating;

    @Ignore
    public Penilaian(int idOutlet) {
        this.setIdOutlet(idOutlet);
        this.tanggalPenilaian = new Date();
    }

    @Ignore
    public Penilaian() {

    }

    public Penilaian(int id, int idOutlet, Date tanggalPenilaian, Bitmap tandaTangan) {
        this.setId(id);
        this.setIdOutlet(idOutlet);
        this.setTanggalPenilaian(tanggalPenilaian);
        this.setTandaTangan(tandaTangan);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(int idOutlet) {
        this.idOutlet = idOutlet;
    }

    public Date getTanggalPenilaian() {
        return tanggalPenilaian;
    }

    public void setTanggalPenilaian(Date tanggalPenilaian) {
        this.tanggalPenilaian = tanggalPenilaian;
    }

    @Nullable
    public Bitmap getTandaTangan() {
        return tandaTangan;
    }

    public void setTandaTangan(@Nullable Bitmap tandaTangan) {
        this.tandaTangan = tandaTangan;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean rating) {
        isRating = rating;
    }
}
