package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Created by sigit on 21/06/2018.
 */

@Entity
public class Sales {

    @PrimaryKey
    @NonNull
    private String salesId;
    private String namaSales;
    private String password;



    public Sales(String salesId, String password, String namaSales) {
        this.setSalesId(salesId);
        this.setPassword(password);
        this.setNamaSales(namaSales);
    }



    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getNamaSales() {
        return namaSales;
    }

    public void setNamaSales(String namaSales) {
        this.namaSales = namaSales;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
