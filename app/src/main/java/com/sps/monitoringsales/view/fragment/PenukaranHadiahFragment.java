package com.sps.monitoringsales.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Hadiah;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;
import com.sps.monitoringsales.model.QueryPenukaranHadiah;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.activity.UtamaActivity;
import com.sps.monitoringsales.viewmodel.PenukaranHadiahFragmentViewModel;

import java.text.DateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 19/06/2018.
 */

public class PenukaranHadiahFragment extends Fragment {

    private static final  DateFormat sDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    private static final String KEY_AKUN_ID = "com.monitoringsales.penukaranhadiah.key.akunid";

    private ViewSwitcher mSwitcher;
    private RecyclerView mRecylerView;
    private TextView mTextLabel;
    private String mAkunId;

    private PenukaranHadiahFragmentViewModel mViewModel;


    public static Fragment newInstance(String idAkun) {
        Fragment fragment = new PenukaranHadiahFragment();
        Bundle arg = new Bundle();
        arg.putString(KEY_AKUN_ID, idAkun);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PenukaranHadiahFragmentViewModel.class);
        mAkunId = getArguments().getString(KEY_AKUN_ID);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UtamaActivity activity = (UtamaActivity) getActivity();
        activity.setTitleAndAction(R.string.label_daftar_penukaran_hadiah, null, false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_layout_stub, container, false);

        mSwitcher = v.findViewById(R.id.list_layout_switcher);
        mRecylerView = v.findViewById(R.id.list_layout_recyclerView);
        mTextLabel = v.findViewById(R.id.list_layout_textView);

        mTextLabel.setText(R.string.list_penukaran_hadiah_label);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mViewModel.getAllPenukaranHadiah(mAkunId).observe(this, list -> {
            if(list != null) {
                if (list.size() > 0) {
                    showListItem(true);
                    mRecylerView.setAdapter(new PenukaranHadiahAdapter(list));
                }
            }
        });


        return v;
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




    class PenukaranHadiahHolder extends RecyclerView.ViewHolder {

        private CircleImageView mFoto;
        private TextView mNamaOutlet;
        private TextView mTanggalPenukaran;
        private TextView mJumlahGelas;
        private TextView mJumlahPiring;
        private TextView mJumlahMangkok;


        public PenukaranHadiahHolder(View itemView) {
            super(itemView);
            mFoto = itemView.findViewById(R.id.list_penukaran_hadiah_foto);
            mNamaOutlet = itemView.findViewById(R.id.list_penukaran_hadiah_nama_outlet);
            mTanggalPenukaran = itemView.findViewById(R.id.list_penukaran_hadiah_tanggal);
            mJumlahGelas = itemView.findViewById(R.id.list_penukaran_hadiah_jumlah_gelas);
            mJumlahPiring = itemView.findViewById(R.id.list_penukaran_hadiah_jumlah_piring);
            mJumlahMangkok = itemView.findViewById(R.id.list_penukaran_hadiah_jumlah_mangkok);
        }

        public void bindItem(QueryPenukaranHadiah query) {
            mFoto.setImageBitmap(query.getFoto());
            mTanggalPenukaran.setText(sDateFormat.format(query.getTanggalPenukaran()));
            mNamaOutlet.setText(query.getNamaOutlet());
            for(PenukaranHadiah h : query.getListPenukaranHadiah()) {
                if(h.getHadiahId() == Hadiah.ID_GELAS) {
                    if(h.getJumlahHadiah() > 0) {
                        mJumlahGelas.setText(String.valueOf(h.getJumlahHadiah()));
                    }
                }
                if(h.getHadiahId() == Hadiah.ID_PIRING) {
                    if(h.getJumlahHadiah() > 0) {
                        mJumlahPiring.setText(String.valueOf(h.getJumlahHadiah()));
                    }
                }
                if(h.getHadiahId() == Hadiah.ID_MANGKOK) {
                    if(h.getJumlahHadiah() > 0) {
                        mJumlahMangkok.setText(String.valueOf(h.getJumlahHadiah()));
                    }
                }
            }
        }
    }

    class PenukaranHadiahAdapter extends RecyclerView.Adapter<PenukaranHadiahHolder> {


        private List<QueryPenukaranHadiah> mList;

        public PenukaranHadiahAdapter(List<QueryPenukaranHadiah> list) {
            mList = list;
        }

        @Override
        public PenukaranHadiahHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.list_penukaran_hadiah, parent, false);
            return new PenukaranHadiahHolder(v);
        }

        @Override
        public void onBindViewHolder(PenukaranHadiahHolder holder, int position) {
            holder.bindItem(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
