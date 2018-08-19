package com.sps.monitoringsales.model;

/**
 * Created by sigit on 26/07/2018.
 */

public class QueryTotalHadiah {

    private String namaHadiah;
    private float totalHadiah;

    public QueryTotalHadiah(String namaHadiah, float totalHadiah) {
        this.setNamaHadiah(namaHadiah);
        this.setTotalHadiah(totalHadiah);
    }


    public String getNamaHadiah() {
        return namaHadiah;
    }

    public void setNamaHadiah(String namaHadiah) {
        this.namaHadiah = namaHadiah;
    }

    public float getTotalHadiah() {
        return totalHadiah;
    }

    public void setTotalHadiah(float totalHadiah) {
        this.totalHadiah = totalHadiah;
    }
}
