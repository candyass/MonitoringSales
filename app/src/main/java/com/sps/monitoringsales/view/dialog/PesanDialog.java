package com.sps.monitoringsales.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.sps.monitoringsales.R;

/**
 * Created by sigit on 06/05/2018.
 */

public class PesanDialog extends DialogFragment {


    public static final String PESAN_DIALOG_TAG = "com.sps.monitoringsales.pesandialog.tag";

    private String pesan;

    public static DialogFragment newInstance(String pesan) {
        PesanDialog dialog = new PesanDialog();
        dialog.setPesan(pesan);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(pesan);
        builder.setPositiveButton("OK", null);
        builder.setIcon(R.drawable.spslogo);
        return builder.create();
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
