package com.sps.monitoringsales.model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by sigit on 04/06/2018.
 */

public class PenukaranBungkusQuery {
    private int id;
    private Bitmap foto;
    private String namaOutlet;
    private int idPenukaran;
    private Bitmap tandaTangan;
    private Date tanggalPenukaran;
    private boolean ditukar;
    private int totalBungkus;


    public PenukaranBungkusQuery(int id, int idPenukaran, Bitmap foto,
                                 String namaOutlet,Bitmap tandaTangan, Date tanggalPenukaran, boolean ditukar, int totalBungkus) {
        this.id = id;
        this.idPenukaran = idPenukaran;
        this.foto = foto;
        this.namaOutlet = namaOutlet;
        this.tandaTangan = tandaTangan;
        this.tanggalPenukaran = tanggalPenukaran;
        this.setDitukar(ditukar);
        this.totalBungkus = totalBungkus;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPenukaran() {
        return idPenukaran;
    }

    public void setIdPenukaran(int idPenukaran) {
        this.idPenukaran = idPenukaran;
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

    public Date getTanggalPenukaran() {
        return tanggalPenukaran;
    }

    public void setTanggalPenukaran(Date tanggalPenukaran) {
        this.tanggalPenukaran = tanggalPenukaran;
    }

    public int getTotalBungkus() {
        return totalBungkus;
    }

    public void setTotalBungkus(int totalBungkus) {
        this.totalBungkus = totalBungkus;
    }

    public boolean isDitukar() {
        return ditukar;
    }

    public void setDitukar(boolean ditukar) {
        this.ditukar = ditukar;
    }

    public Bitmap getTandaTangan() {
        return tandaTangan;
    }

    public void setTandaTangan(Bitmap tandaTangan) {
        this.tandaTangan = tandaTangan;
    }
}
