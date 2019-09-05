package com.sps.monitoringsales.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.view.activity.MonitoringBungkusActivity;
import com.sps.monitoringsales.view.activity.MonitoringHadiahActivity;
import com.sps.monitoringsales.viewmodel.DaftarSalesFragmentViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 24/09/2018.
 */

public class DaftarSalesFragment extends Fragment {


    private static final int FRAGMENT_BUNGKUS = 100;
    private static final int FRAGMENT_HADIAH = 101;

    private TextView mTextJudul;
    private RecyclerView mRecyclerView;
    private DaftarSalesFragmentViewModel mViewModel;
    private int tipeFragment;



    public static Fragment newFragmentBungkus() {
        DaftarSalesFragment fragment = new DaftarSalesFragment();
        fragment.setTipeFragment(FRAGMENT_BUNGKUS);
        return fragment;
    }

    public static Fragment newFragmentHadiah() {
        DaftarSalesFragment fragment = new DaftarSalesFragment();
        fragment.setTipeFragment(FRAGMENT_HADIAH);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_sales, container, false);
        mTextJudul = view.findViewById(R.id.fragment_daftar_sales_text);
        mRecyclerView = view.findViewById(R.id.fragment_daftar_sales_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(tipeFragment == FRAGMENT_HADIAH) {
            mTextJudul.setText("Penukaran Hadiah");
        }else {
            mTextJudul.setText("Penukaran Bungkus");
        }

        mViewModel = ViewModelProviders.of(this).get(DaftarSalesFragmentViewModel.class);
        mViewModel.getAllAkun().observe(this, list -> {
            if(list != null) {
                mRecyclerView.setAdapter(new SalesAdapter(list));
            }
        });

        return view;
    }

    protected void setTipeFragment(int tipeFragment) {
        this.tipeFragment = tipeFragment;
    }

    public boolean isBungkusFragment() {
        return tipeFragment == FRAGMENT_BUNGKUS;
    }

    public boolean isHadiahFragment() {
        return tipeFragment == FRAGMENT_HADIAH;
    }


    class SalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView mFoto;
        private TextView mTextNama;
        private TextView mTextId;
        private Akun mAkun;

        public SalesViewHolder(View itemView) {
            super(itemView);
            mFoto = itemView.findViewById(R.id.list_sales_foto);
            mTextNama = itemView.findViewById(R.id.list_sales_nama);
            mTextId = itemView.findViewById(R.id.list_sales_akun_id);
            itemView.setOnClickListener(this);
        }

        public void bindItem(Akun akun) {
            mFoto.setImageResource(R.drawable.ic_profil_sales);
            mAkun = akun;
            mTextNama.setText(mAkun.getNamaPengguna());
            mTextId.setText(mAkun.getAkunId());
        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            if(tipeFragment == FRAGMENT_BUNGKUS) {
                intent = MonitoringBungkusActivity.newIntent(getContext(),mAkun.getAkunId());
            }else {
                intent = MonitoringHadiahActivity.newIntent(getContext(), mAkun.getAkunId());
            }
            startActivity(intent);
        }
    }

    class SalesAdapter extends RecyclerView.Adapter<SalesViewHolder> {

        private List<Akun> list;

        public SalesAdapter(List<Akun> list) {
            this.list = list;
        }

        @Override
        public SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.list_sales, parent, false);
            return new SalesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SalesViewHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
