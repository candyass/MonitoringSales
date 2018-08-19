package com.sps.monitoringsales.view.dialog;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.model.TukarHadiahEvent;
import com.sps.monitoringsales.viewmodel.TandaTanganDialogViewModel;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sigit on 23/06/2018.
 */

public class TandaTanganDialog extends DialogFragment {


    public static String ARG_PENUKARAN_ID = "com.sps.monitoring.sales.arg.penukaranid";

    private SignaturePad mSignaturePad;
    private Button mHapusButton;
    private Button mSimpanButton;

    private int mIdPenukaran;
    private TandaTanganDialogViewModel mViewModel;


    public static DialogFragment newInstance(int idPenukaran) {
        Bundle arg = new Bundle();
        arg.putInt(ARG_PENUKARAN_ID, idPenukaran);
        DialogFragment dialogFragment = new TandaTanganDialog();
        dialogFragment.setArguments(arg);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TandaTanganDialogViewModel.class);
        mIdPenukaran = getArguments().getInt(ARG_PENUKARAN_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tanda_tangan, container, false);

        mSignaturePad = view.findViewById(R.id.dialog_tanda_tangan_pad);
        mHapusButton = view.findViewById(R.id.dialog_tanda_tangan_hapus_button);
        mSimpanButton = view.findViewById(R.id.dialog_tanda_tangan_simpan_button);

        mHapusButton.setEnabled(false);
        mSimpanButton.setEnabled(false);

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                mHapusButton.setEnabled(true);
                mSimpanButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mHapusButton.setEnabled(false);
                mSimpanButton.setEnabled(false);
            }
        });

        mSimpanButton.setOnClickListener(v -> {
            Bitmap ttd = mSignaturePad.getSignatureBitmap();
            mViewModel.saveTandaTangan(mIdPenukaran, ttd);
            dismiss();
            EventBus.getDefault().post(new TukarHadiahEvent(true));

        });

        mHapusButton.setOnClickListener(v -> {
            mSignaturePad.clear();
        });





        return view;
    }
}
