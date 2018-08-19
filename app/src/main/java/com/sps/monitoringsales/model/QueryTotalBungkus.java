package com.sps.monitoringsales.model;

/**
 * Created by sigit on 26/07/2018.
 */

public class QueryTotalBungkus {

    private String namaBungkus;
    private float totalBungkus;

    public QueryTotalBungkus(String namaBungkus, float totalBungkus) {
        this.setNamaBungkus(namaBungkus);
        this.setTotalBungkus(totalBungkus);
    }


    public String getNamaBungkus() {
        return namaBungkus;
    }

    public void setNamaBungkus(String namaBungkus) {
        this.namaBungkus = namaBungkus;
    }

    public float getTotalBungkus() {
        return totalBungkus;
    }

    public void setTotalBungkus(float totalBungkus) {
        this.totalBungkus = totalBungkus;
    }
}


