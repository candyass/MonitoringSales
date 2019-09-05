package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Penilaian.class, parentColumns = "id", childColumns = "idKeluhanSaran"),
                @ForeignKey(entity = Bungkus.class, parentColumns = "idBungkus", childColumns = "idBungkus")},
        primaryKeys = {"idKeluhanSaran", "idBungkus"})
public class KeluhanSaran {

    private int idKeluhanSaran;
    private int idBungkus;
    private double rating;
    private String saran;


    @Ignore
    public KeluhanSaran(int idBungkus, double rating) {
        setIdBungkus(idBungkus);
        setRating(rating);
    }

    @Ignore
    public KeluhanSaran(int idBungkus) {
        setIdBungkus(idBungkus);
    }

    public KeluhanSaran(int idKeluhanSaran, int idBungkus, double rating, String saran) {
        setIdKeluhanSaran(idKeluhanSaran);
        setIdBungkus(idBungkus);
        setRating(rating);
        setSaran(saran);
    }


    public int getIdKeluhanSaran() {
        return idKeluhanSaran;
    }

    public void setIdKeluhanSaran(int idKeluhanSaran) {
        this.idKeluhanSaran = idKeluhanSaran;
    }

    public int getIdBungkus() {
        return idBungkus;
    }

    public void setIdBungkus(int idBungkus) {
        this.idBungkus = idBungkus;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }
}
