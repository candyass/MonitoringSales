package com.sps.monitoringsales.view.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.model.PenukaranBungkusQuery;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.activity.InputPenukaranActivity;
import com.sps.monitoringsales.view.activity.InputPenukaranHadiahActivity;
import com.sps.monitoringsales.view.activity.UtamaActivity;
import com.sps.monitoringsales.viewmodel.PenukaranBungkusFragmentViewModel;

import java.text.DateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 14/03/2018.
 */

public class PenukaranBungkusFragment extends Fragment {


    private static final DateFormat sDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    private static final int REQUEST_TUKAR_HADIAH = 200;

    private ViewSwitcher mSwitcher;
    private RecyclerView mRecylerView;
    private TextView mTextLabel;

    private PenukaranBungkusFragmentViewModel mViewModel;

    public static Fragment newInstance() {
        Fragment fragment = new PenukaranBungkusFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PenukaranBungkusFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_layout_stub, container, false);

        mSwitcher = v.findViewById(R.id.list_layout_switcher);
        mRecylerView = v.findViewById(R.id.list_layout_recyclerView);
        mTextLabel = v.findViewById(R.id.list_layout_textView);

        mTextLabel.setText(R.string.list_penukaran_label);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getAllPenukranBungkus().observe(this, penukaranBungkusQueries -> {
            MyLogger.logPesan("PenukaranBungkusQuery size : " + String.valueOf(penukaranBungkusQueries.size()));
            if(penukaranBungkusQueries != null) {
                if (penukaranBungkusQueries.size() > 0) {
                    showListItem(true);
                    mRecylerView.setAdapter(new PenukaranBungkusAdapter(penukaranBungkusQueries));
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UtamaActivity activity = (UtamaActivity) getActivity();
        activity.setTitleAndAction(R.string.label_daftar_penukaran_bungkus, v -> {
            Intent intent = InputPenukaranActivity.newIntent(getContext());
            startActivity(intent);
        }, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showListItem(boolean value) {
        if (value) {
            if(mSwitcher.getNextView().getId() == R.id.list_layout_recyclerView) {
                mSwitcher.showNext();
            }
        } else {
            if(mSwitcher.getNextView().getId() == R.id.list_layout_textView) {
                mSwitcher.showNext();
            }
        }
    }


    class PenukaranBungkusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView mCardView;
        private CircleImageView mFoto;
        private TextView mNamaOutlet;
        private TextView mTanggal;
        private TextView mTotalBungkus;
        private TextView mDitukar;
        private ImageView mTandaTangan;
        private TextView mTandaTanganText;

        private PenukaranBungkusQuery mQuery;

        public PenukaranBungkusHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.list_penukaran_bungkus_card);
            mFoto = itemView.findViewById(R.id.list_penukaran_bungkus_foto);
            mNamaOutlet = itemView.findViewById(R.id.list_penukaran_bungkus_nama_outlet);
            mTanggal = itemView.findViewById(R.id.list_penukaran_bungkus_tanggal);
            mTotalBungkus = itemView.findViewById(R.id.list_penukaran_bungkus_total_bungkus);
            mDitukar = itemView.findViewById(R.id.list_penukaran_bungkus_ditukar);
            mTandaTangan = itemView.findViewById(R.id.list_penukaran_bungkus_ttd);
            mTandaTanganText = itemView.findViewById(R.id.list_penukaran_bungkus_ttd_text);
            mCardView.setOnClickListener(this);
        }

        public void bindItem(PenukaranBungkusQuery query) {
            mQuery = query;
            mFoto.setImageBitmap(mQuery.getFoto());
            mNamaOutlet.setText(mQuery.getNamaOutlet());
            mTanggal.setText(sDateFormat.format(mQuery.getTanggalPenukaran()));
            mTotalBungkus.setText(String.valueOf(mQuery.getTotalBungkus()) + " Bungkus");

            prosesStatusPenukaran(mQuery.isDitukar());
            if(mQuery.isDitukar()) {
                mCardView.setClickable(false);
            }

            if(query.getTandaTangan() != null) {
                enableTandaTangan(true);
                mTandaTangan.setImageBitmap(query.getTandaTangan());
            }else {
                enableTandaTangan(false);
            }

        }

        private void prosesStatusPenukaran(boolean value) {
            if(value) {
                mDitukar.setText("Ditukar");
                mDitukar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ditukar_24dp, 0, 0, 0);
                mDitukar.setTextColor(getResources().getColor(R.color.colorGreen));
            }else {
                mDitukar.setText("Belum Ditukar");
                mDitukar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_belum_ditukar24dp, 0, 0, 0);
                mDitukar.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }

        private void enableTandaTangan(boolean value) {
            if(value) {
                mTandaTangan.setVisibility(View.VISIBLE);
                mTandaTanganText.setVisibility(View.VISIBLE);
            }else {
                mTandaTangan.setVisibility(View.GONE);
                mTandaTanganText.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = InputPenukaranHadiahActivity.newIntent(getContext(), mQuery.getNamaOutlet(), mQuery.getIdPenukaran(), mQuery.getTotalBungkus());
            startActivityForResult(intent, REQUEST_TUKAR_HADIAH);
        }
    }


    class PenukaranBungkusAdapter extends RecyclerView.Adapter<PenukaranBungkusHolder> {

        private List<PenukaranBungkusQuery> list;

        public PenukaranBungkusAdapter(List<PenukaranBungkusQuery> list) {
            this.list = list;
        }

        @Override
        public PenukaranBungkusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            PenukaranBungkusHolder holder = new PenukaranBungkusHolder(inflater.
                    inflate(R.layout.list_penukaran_bungkus, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(PenukaranBungkusHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
