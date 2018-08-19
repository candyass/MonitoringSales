package com.sps.monitoringsales.model;

/**
 * Created by sigit on 19/04/2018.
 */

public class SelectedBungkus {
    private int idBungkus;
    private String namaBungkus;
    private int jumlahBungkus;

    public SelectedBungkus(int idBungkus, String namaBungkus, int jumlahBungkus) {
        this.setIdBungkus(idBungkus);
        this.setNamaBungkus(namaBungkus);
        this.setJumlahBungkus(jumlahBungkus);
    }


    public void setIdBungkus(int idBungkus) {
        this.idBungkus = idBungkus;
    }

    public int getIdBungkus() {
        return idBungkus;
    }

    public String getNamaBungkus() {
        return namaBungkus;
    }

    public void setNamaBungkus(String namaBungkus) {
        this.namaBungkus = namaBungkus;
    }

    public int getJumlahBungkus() {
        return jumlahBungkus;
    }

    public void setJumlahBungkus(int jumlahBungkus) {
        this.jumlahBungkus = jumlahBungkus;
    }
}
