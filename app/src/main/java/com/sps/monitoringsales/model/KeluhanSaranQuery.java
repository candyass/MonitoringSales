package com.sps.monitoringsales.model;

public class KeluhanSaranQuery {

    private int idKeluhanSaran;
    private int idBungkus;
    private double rating;
    private String namaBungkus;

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

    public String getNamaBungkus() {
        return namaBungkus;
    }

    public void setNamaBungkus(String namaBungkus) {
        this.namaBungkus = namaBungkus;
    }

    public int getIdKeluhanSaran() {

        return idKeluhanSaran;
    }

    public void setIdKeluhanSaran(int idKeluhanSaran) {
        this.idKeluhanSaran = idKeluhanSaran;
    }
}
