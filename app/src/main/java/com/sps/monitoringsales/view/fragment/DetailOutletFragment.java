package com.sps.monitoringsales.view.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.viewmodel.DetailOutletFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOutletFragment extends Fragment {


    private ImageView mCallButton;
    private TextView mNamaOutletText;
    private TextView mNoTeleponText;
    private ImageView mFotoOutlet;
    private TextView mAlamatText;
    private TextView mKategoriText;

    DetailOutletFragmentViewModel mViewModel;


    public static Fragment newInstance() {
        Fragment fragment = new DetailOutletFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailOutletFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detail_outlet, container, false);

        mFotoOutlet = v.findViewById(R.id.activity_detail_foto_outlet);
        mNamaOutletText = v.findViewById(R.id.activity_detail_nama_text);
        mNoTeleponText = v.findViewById(R.id.activity_detail_no_telepon_text);
        mAlamatText = v.findViewById(R.id.activity_detail_alamat_text);
        mKategoriText = v.findViewById(R.id.activity_detail_kategori_outlet_text);
        mCallButton = v.findViewById(R.id.activity_detail_call);



        Outlet outlet = mViewModel.getOutlet();

        mFotoOutlet.setImageBitmap(outlet.getFoto());
        mNamaOutletText.setText(outlet.getNamaOutlet());
        mNoTeleponText.setText(outlet.getNoTelepon());
        mAlamatText.setText(outlet.getLokasiOutlet().getAlamat());
        mKategoriText.setText(outlet.getKategoriOutlet());

        return v;
    }

}
