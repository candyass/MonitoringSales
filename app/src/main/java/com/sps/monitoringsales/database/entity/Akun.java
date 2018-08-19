package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by sigit on 04/07/2018.
 */

@Entity
public class Akun {

    public static final int LOGIN_SALES = 1;
    public static final int LOGIN_ADMIN = 2;


    @PrimaryKey
    @NonNull
    private String akunId;
    private String password;
    private String namaPengguna;
    private int loginSebagai;

    public Akun(String akunId, String password,String namaPengguna, int loginSebagai) {
        this.setAkunId(akunId);
        this.setPassword(password);
        this.setNamaPengguna(namaPengguna);
        this.setLoginSebagai(loginSebagai);
    }


    public String getAkunId() {
        return akunId;
    }

    public void setAkunId(String akunId) {
        this.akunId = akunId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginSebagai() {
        return loginSebagai;
    }

    public void setLoginSebagai(int loginSebagai) {
        this.loginSebagai = loginSebagai;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }
}
