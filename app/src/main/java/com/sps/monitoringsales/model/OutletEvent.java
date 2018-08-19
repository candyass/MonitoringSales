package com.sps.monitoringsales.model;

import android.graphics.Bitmap;

/**
 * Created by sigit on 05/05/2018.
 */

public class OutletEvent {

    private int outletId;
    private Bitmap bitmap;
    private String namaOutlet;

    public OutletEvent(int outletId, Bitmap bitmap, String namaOutlet) {
        this.setOutletId(outletId);
        this.setBitmap(bitmap);
        this.setNamaOutlet(namaOutlet);
    }


    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }
}
