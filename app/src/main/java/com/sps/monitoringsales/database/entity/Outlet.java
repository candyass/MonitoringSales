package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigit on 17/03/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Akun.class,
        parentColumns = "akunId", childColumns = "idAkun"))
public class Outlet {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Bitmap foto;
    private String namaPemilik;
    private String namaOutlet;
    private String jenisOutlet;
    private String kategoriOutlet;
    private String noTelepon;
    private String idAkun;
    @Embedded
    private LokasiOutlet lokasiOutlet;


    private static final String JENIS_OUTLET_RO = "RO";
    private static final String JENIS_OUTLET_NOO = "NOO";


    @Ignore
    public Outlet() {
        jenisOutlet = JENIS_OUTLET_NOO;
    }

    public Outlet(int id, Bitmap foto, String namaPemilik, String namaOutlet, String jenisOutlet,
                  String kategoriOutlet, String noTelepon, LokasiOutlet lokasiOutlet, String idAkun ) {
        this.id = id;
        this.foto = foto;
        this.namaPemilik = namaPemilik;
        this.namaOutlet = namaOutlet;
        this.jenisOutlet = jenisOutlet;
        this.kategoriOutlet = kategoriOutlet;
        this.noTelepon = noTelepon;
        this.lokasiOutlet = lokasiOutlet;
        this.idAkun = idAkun;
    }

    @Ignore
    public Outlet(String namaPemilik, String namaOutlet, String noTelepon) {
        this.namaPemilik = namaPemilik;
        this.namaOutlet = namaOutlet;
        this.noTelepon = noTelepon;
        jenisOutlet = JENIS_OUTLET_NOO;
    }

    @Ignore
    public Outlet(int id, String namaPemilik, String jenisOutlet) {
        this.id = id;
        this.namaPemilik = namaPemilik;
        this.jenisOutlet = jenisOutlet;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getJenisOutlet() {
        return jenisOutlet;
    }

    public void setJenisOutlet(String jenisOutlet) {
        this.jenisOutlet = jenisOutlet;
    }


    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public String getKategoriOutlet() {
        return kategoriOutlet;
    }

    public void setKategoriOutlet(String kategoriOutlet) {
        this.kategoriOutlet = kategoriOutlet;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public LokasiOutlet getLokasiOutlet() {
        return lokasiOutlet;
    }

    public void setLokasiOutlet(LokasiOutlet lokasiOutlet) {
        this.lokasiOutlet = lokasiOutlet;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public static List<Outlet> getMockOutlets() {
        List<Outlet> list = new ArrayList<>();
        list.add(new Outlet(1, "Sigit Outlet", JENIS_OUTLET_NOO));
        list.add(new Outlet(2, "Ari Outlet", JENIS_OUTLET_NOO));
        list.add(new Outlet(3, "Ergi Outlet", JENIS_OUTLET_NOO));
        list.add(new Outlet(4, "Dicky Outlet", JENIS_OUTLET_NOO));
        list.add(new Outlet(5, "Riazky Outlet", JENIS_OUTLET_RO));
        list.add(new Outlet(6, "Arif Outlet", JENIS_OUTLET_RO));
        list.add(new Outlet(7, "Andri Outlet", JENIS_OUTLET_RO));
        list.add(new Outlet(8, "Iman Outlet", JENIS_OUTLET_RO));
        list.add(new Outlet(9, "Azis Outlet", JENIS_OUTLET_RO));
        return list;

    }


}
