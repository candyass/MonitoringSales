package com.sps.monitoringsales.model;

import android.graphics.Bitmap;

import java.util.Date;

public class PenilaianKeluhanQuery {

    private int id;
    private Bitmap foto;
    private String namaOutlet;
    private int idKeluhanSaran;
    private Bitmap tandaTangan;
    private Date tanggalPenilaian;
    private boolean isRating;
    private int totalBungkus;

    public int getTotalBungkus() {
        return totalBungkus;
    }

    public void setTotalBungkus(int totalBungkus) {
        this.totalBungkus = totalBungkus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public Bitmap getTandaTangan() {
        return tandaTangan;
    }

    public void setTandaTangan(Bitmap tandaTangan) {
        this.tandaTangan = tandaTangan;
    }

    public Date getTanggalPenilaian() {
        return tanggalPenilaian;
    }

    public void setTanggalPenilaian(Date tanggalPenilaian) {
        this.tanggalPenilaian = tanggalPenilaian;
    }


    public int getIdKeluhanSaran() {
        return idKeluhanSaran;
    }

    public void setIdKeluhanSaran(int idKeluhanSaran) {
        this.idKeluhanSaran = idKeluhanSaran;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean rating) {
        isRating = rating;
    }
}
