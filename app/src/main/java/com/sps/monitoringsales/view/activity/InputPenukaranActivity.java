package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.KeluhanSaran;
import com.sps.monitoringsales.database.entity.Penilaian;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranBungkus;
import com.sps.monitoringsales.model.OutletEvent;
import com.sps.monitoringsales.model.SelectedBungkus;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.dialog.ListBungkusDialog;
import com.sps.monitoringsales.view.dialog.ListOutletDialog;
import com.sps.monitoringsales.view.dialog.PesanDialog;
import com.sps.monitoringsales.viewmodel.InputPenukaranActivityViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InputPenukaranActivity extends AppCompatActivity {


    private static final String DIALOG_BUNGKUS_TAG = "ListBungkusDialog";
    private static final String DIALOG_OUTLET_TAG = "ListOutletDialog";
    private static final String DIALOG_PESAN_TAG = "PesanDialog";
    private static final String EXTRA_AKUN_ID = "com.monitoringsales.inputpenukaran.extra.akunid";

    private CircleImageView mGambarOutlet;
    private TextView mNamaOutlet;
    private FloatingActionButton mFabTambah;
    private Button mTukarButton;
    private RecyclerView mRecyclerView;
    private TextView mTotalBungkusText;
    private TextView mTanggal;
    private LinearLayout mLinearLayout;
    private String mAkunId;

    private InputPenukaranActivityViewModel mViewModel;

    private PenukaranAdapter mAdapter;
    private int mTotalBungkus = 0;



    public static Intent newIntent(Context context, String idAkun) {
        Intent intent = new Intent(context, InputPenukaranActivity.class);
        intent.putExtra(EXTRA_AKUN_ID,idAkun);
        return intent;
    }

    // LifeCycle Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penukaran);
        mAkunId = getIntent().getStringExtra(EXTRA_AKUN_ID);

        initializeViews();

        mViewModel = ViewModelProviders.of(this).get(InputPenukaranActivityViewModel.class);

        updateOutlet();

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        mTanggal.setText(dateFormat.format(date));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mAdapter = new PenukaranAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mFabTambah.setOnClickListener(v -> {
            DialogFragment dialogBungkus = ListBungkusDialog.newInstance();
            dialogBungkus.show(getSupportFragmentManager(), DIALOG_BUNGKUS_TAG);
        });

        mLinearLayout.setOnClickListener(v -> {
            DialogFragment dialogOutlet = ListOutletDialog.newInstance(mAkunId);
            dialogOutlet.show(getSupportFragmentManager(), DIALOG_OUTLET_TAG);
        });

        mTukarButton.setOnClickListener(v -> {
            if(mViewModel.getmOutletEvent() == null) {
                DialogFragment dialog = PesanDialog.newInstance("Outlet belum dipilih");
                dialog.show(getSupportFragmentManager(), DIALOG_PESAN_TAG);
                return;
            }
            if (mAdapter.getItemCount() <= 0) {
                DialogFragment dialog = PesanDialog.newInstance("Belum ada bungkus yang dipilih");
                dialog.show(getSupportFragmentManager(), DIALOG_PESAN_TAG);
                return;
            }
//            List<PenukaranBungkus> listPenukaranBungkus = new ArrayList<>();
            List<KeluhanSaran> listKeluhanSaran = new ArrayList<>();
            for(SelectedBungkus s : mAdapter.getListSelectecBungkus()) {
//                listPenukaranBungkus.add(new PenukaranBungkus(s.getIdBungkus(), s.getJumlahBungkus()));
                listKeluhanSaran.add(new KeluhanSaran(s.getIdBungkus()));
            }
            Penukaran penukaran = new Penukaran(mViewModel.getmOutletEvent().getOutletId());
            Penilaian penilaian = new Penilaian(mViewModel.getmOutletEvent().getOutletId());
//            mViewModel.simpanAllSelectedBungkus(penukaran, listPenukaranBungkus);
            mViewModel.simpanAllPenilaianBungkus(penilaian, listKeluhanSaran);
            finish();

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Private Methods

    private void initializeViews() {
        mGambarOutlet = findViewById(R.id.input_penukaran_gambar_outlet);
        mNamaOutlet = findViewById(R.id.input_penukaran_nama_outlet);
        mFabTambah = findViewById(R.id.input_penukaran_fab_tambah);
        mRecyclerView = findViewById(R.id.input_penukaran_recyclerView);
        mTotalBungkusText = findViewById(R.id.input_penukaran_total_bungkus_text);
        mTukarButton = findViewById(R.id.input_penukaran_tukar_button);
        mTanggal = findViewById(R.id.input_penukaran_tanggal_text);
        mLinearLayout = findViewById(R.id.input_penukaran_linearLayout);
    }

    private void tambahTotalBungkus(int jumlah) {
        mTotalBungkus += jumlah;
        mTotalBungkusText.setText(String.valueOf(mTotalBungkus));
    }

    private void kurangTotalBungkus(int jumlah) {
        mTotalBungkus -= jumlah;
        mTotalBungkusText.setText(String.valueOf(mTotalBungkus));
    }


    // EventBus Callback

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBungkus(SelectedBungkus selectedBungkus) {
        mAdapter.tambahItem(selectedBungkus);
        mTotalBungkus++;
        mTotalBungkusText.setText(String.valueOf(mTotalBungkus));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOutlet(OutletEvent event) {
        if(event != null) {
            mViewModel.setOutletEvent(event);
            updateOutlet();
        }
    }


    private void updateOutlet() {
        if(mViewModel.getmOutletEvent() != null) {
            mNamaOutlet.setText(mViewModel.getmOutletEvent().getNamaOutlet());
            mGambarOutlet.setImageBitmap(mViewModel.getmOutletEvent().getBitmap());
        }
    }


    // Inner Classes

    class PenukaranHolder extends RecyclerView.ViewHolder {

        private TextView mTextNamaBungkus;
        private int jumlahBungkus;

        private SelectedBungkus mSelectedBungkus;


        public PenukaranHolder(View itemView) {
            super(itemView);
            mTextNamaBungkus = itemView.findViewById(R.id.list_penukaran_text_bungkus);
            jumlahBungkus = 0;

        }

        public void bingItem(SelectedBungkus selectedBungkus) {
            mSelectedBungkus = selectedBungkus;
            mTextNamaBungkus.setText(mSelectedBungkus.getNamaBungkus());
        }

        private void kurangJumlahBungkus(int angka) {
            jumlahBungkus -= angka;
            mSelectedBungkus.setJumlahBungkus(jumlahBungkus);
            kurangTotalBungkus(angka);
        }

        private void tambahJumlahBungkus(int angka) {
            jumlahBungkus += angka;
            mSelectedBungkus.setJumlahBungkus(jumlahBungkus);
            tambahTotalBungkus(angka);
        }
    }

    class PenukaranAdapter extends RecyclerView.Adapter<PenukaranHolder> {

        private List<SelectedBungkus> mListSelectedBungkus;

        public PenukaranAdapter() {
            mListSelectedBungkus = new ArrayList<>();
        }

        public PenukaranAdapter(List<SelectedBungkus> listSelectedBungkus) {
            mListSelectedBungkus = listSelectedBungkus;
        }

        @Override
        public PenukaranHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getBaseContext());
            View v = inflater.inflate(R.layout.list_penukaran, parent, false);
            PenukaranHolder holder = new PenukaranHolder(v);
            return holder;
        }

        public void tambahItem(SelectedBungkus selectedBungkus) {
            if(selectedBungkus != null) {
                mListSelectedBungkus.add(selectedBungkus);
                notifyItemInserted(mListSelectedBungkus.size() + 1);
            }
        }

        public List<SelectedBungkus> getListSelectecBungkus() {
            return mListSelectedBungkus;
        }

        @Override
        public void onBindViewHolder(PenukaranHolder holder, int position) {
            holder.bingItem(mListSelectedBungkus.get(position));
        }

        @Override
        public int getItemCount() {
            return mListSelectedBungkus.size();
        }
    }


}
