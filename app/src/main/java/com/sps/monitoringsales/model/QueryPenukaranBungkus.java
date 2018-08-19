package com.sps.monitoringsales.model;

import android.graphics.Bitmap;

import com.sps.monitoringsales.database.entity.Bungkus;

import java.util.Date;
import java.util.List;

/**
 * Created by sigit on 27/05/2018.
 */

public class QueryPenukaranBungkus {
    private int penukaranId;
    private String namaOutlet;
    private Bitmap foto;
    private Date tanggalPenukran;
    private List<Bungkus> listBungkus;


    public QueryPenukaranBungkus(int penukaranId, String namaOutlet,
                                    Bitmap foto, Date tanggalPenukran, List<Bungkus> listBungkus) {
        this.setPenukaranId(penukaranId);
        this.setNamaOutlet(namaOutlet);
        this.setFoto(foto);
        this.setTanggalPenukran(tanggalPenukran);
        this.setListBungkus(listBungkus);
    }


    public int getPenukaranId() {
        return penukaranId;
    }

    public void setPenukaranId(int penukaranId) {
        this.penukaranId = penukaranId;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Date getTanggalPenukran() {
        return tanggalPenukran;
    }

    public void setTanggalPenukran(Date tanggalPenukran) {
        this.tanggalPenukran = tanggalPenukran;
    }

    public List<Bungkus> getListBungkus() {
        return listBungkus;
    }

    public void setListBungkus(List<Bungkus> listBungkus) {
        this.listBungkus = listBungkus;
    }
}
