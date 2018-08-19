package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Ignore;

/**
 * Created by sigit on 15/04/2018.
 */

public class LokasiOutlet {
    private double latitude;
    private double longtitude;
    private String alamat;

    @Ignore
    public LokasiOutlet() {

    }

    @Ignore
    public LokasiOutlet(String alamat) {
        this.setAlamat(alamat);
    }

    public LokasiOutlet(String alamat, double latitude, double longtitude) {
        this.setAlamat(alamat);
        this.setLatitude(latitude);
        this.setLongtitude(longtitude);
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
