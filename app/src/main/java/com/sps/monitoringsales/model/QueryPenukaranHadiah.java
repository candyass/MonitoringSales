package com.sps.monitoringsales.model;

import android.arch.persistence.room.Relation;
import android.graphics.Bitmap;

import com.sps.monitoringsales.database.entity.PenukaranHadiah;

import java.util.Date;
import java.util.List;

/**
 * Created by sigit on 19/06/2018.
 */

public class QueryPenukaranHadiah {

    private int id;
    private Bitmap foto;
    private String namaOutlet;
    private Date tanggalPenukaran;

    @Relation(parentColumn = "id", entityColumn = "penukaranId", entity = PenukaranHadiah.class)
    private List<PenukaranHadiah> listPenukaranHadiah;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTanggalPenukaran() {
        return tanggalPenukaran;
    }

    public void setTanggalPenukaran(Date tanggalPenukaran) {
        this.tanggalPenukaran = tanggalPenukaran;
    }

    public List<PenukaranHadiah> getListPenukaranHadiah() {
        return listPenukaranHadiah;
    }

    public void setListPenukaranHadiah(List<PenukaranHadiah> listPenukaranHadiah) {
        this.listPenukaranHadiah = listPenukaranHadiah;
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
}
