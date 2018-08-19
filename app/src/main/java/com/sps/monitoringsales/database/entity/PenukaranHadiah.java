package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

/**
 * Created by sigit on 17/06/2018.
 */

@Entity(foreignKeys = {
                @ForeignKey(entity = Penukaran.class, parentColumns = "id", childColumns = "penukaranId"),
                @ForeignKey(entity = Hadiah.class, parentColumns = "idHadiah", childColumns = "hadiahId")},
        primaryKeys = {"penukaranId","hadiahId"})
public class PenukaranHadiah {

    private int penukaranId;
    private int hadiahId;
    private int jumlahHadiah;

    @Ignore
    public PenukaranHadiah(int penukaranId, int hadiahId) {
        this.setPenukaranId(penukaranId);
        this.setHadiahId(hadiahId);
    }

    public PenukaranHadiah(int penukaranId, int hadiahId, int jumlahHadiah) {
        this.setPenukaranId(penukaranId);
        this.setHadiahId(hadiahId);
        this.setJumlahHadiah(jumlahHadiah);
    }


    public int getPenukaranId() {
        return penukaranId;
    }

    public void setPenukaranId(int penukaranId) {
        this.penukaranId = penukaranId;
    }

    public int getHadiahId() {
        return hadiahId;
    }

    public void setHadiahId(int hadiahId) {
        this.hadiahId = hadiahId;
    }

    public int getJumlahHadiah() {
        return jumlahHadiah;
    }

    public void setJumlahHadiah(int jumlahHadiah) {
        this.jumlahHadiah = jumlahHadiah;
    }
}
