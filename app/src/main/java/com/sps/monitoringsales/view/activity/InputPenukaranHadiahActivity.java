package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Hadiah;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;
import com.sps.monitoringsales.model.TukarHadiahEvent;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.dialog.PesanDialog;
import com.sps.monitoringsales.view.dialog.TandaTanganDialog;
import com.sps.monitoringsales.viewmodel.InputPenukaranHadiahActivityViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class InputPenukaranHadiahActivity extends AppCompatActivity  {



    private static final String EXTRA_NAMA_OUTLET = "com.sps.monitoringsales.extra.namaoutlet";
    private static final String EXTRA_ID_PENUKARAN = "com.sps.monitoringsales.extra.idpenukaran";
    private static final String EXTRA_TOTAL_BUNGKUS = "com.sps.monitoringsales.extra.totalbungkus";
    private static final String DIALOG_TTD_TAG = "com.sps.monitoringsales.dialog.tag.tandatangandialog";

    private TextView mTotalHadiahText;
    private TextView mTotalBungkusText;
    private TextView mJumlahGelas;
    private TextView mJumlahPiring;
    private TextView mJumlahMangkok;
    private TextView mNamaOutletText;

    private ImageView mTambahGelas;
    private ImageView mKurangGelas;
    private ImageView mTambahPiring;
    private ImageView mKurangPiring;
    private ImageView mTambahMangkok;
    private ImageView mKurangMangkok;

    private Button mTukarButton;

    private InputPenukaranHadiahActivityViewModel mViewModel;

    private int mIdPenukaran;
    private int mJumlahHadiah;
    private Penukaran mPenukaran;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, InputPenukaranHadiahActivity.class);
        return intent;
    }

    public static Intent newIntent(Context context, String namaOutlet, int idPenukaran, int totalBungkus) {
        Intent intent = new Intent(context, InputPenukaranHadiahActivity.class);
        intent.putExtra(EXTRA_NAMA_OUTLET, namaOutlet);
        intent.putExtra(EXTRA_ID_PENUKARAN, idPenukaran);
        intent.putExtra(EXTRA_TOTAL_BUNGKUS, totalBungkus);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_penukaran_hadiah);

        initViews();


        Intent requestedIntent = getIntent();
        if(requestedIntent != null) {
            mIdPenukaran = requestedIntent.getIntExtra(EXTRA_ID_PENUKARAN, -1);
            mNamaOutletText.setText(requestedIntent.getStringExtra(EXTRA_NAMA_OUTLET));
            mTotalBungkusText.setText(String.valueOf(requestedIntent.getIntExtra(EXTRA_TOTAL_BUNGKUS, -1)));
        }

        mViewModel = ViewModelProviders.of(this).get(InputPenukaranHadiahActivityViewModel.class);
        mViewModel.getPenukaran(mIdPenukaran).observe(this, penukaran -> {
            if(penukaran != null) {
                mPenukaran = penukaran;
            }
        });

        mJumlahHadiah = Integer.parseInt(mTotalBungkusText.getText().toString()) / 24;
        mTotalHadiahText.setText(String.valueOf(mJumlahHadiah));

        setupListener();

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTandaTanganEvent(TukarHadiahEvent event) {
        if(event.isTertukar()) {
            finish();
        }

    }

    private void setupListener() {
        mTambahGelas.setOnClickListener(v -> {
            tambahJumlah(mJumlahGelas);
        });
        mKurangGelas.setOnClickListener(v -> {
            kurangJumlah(mJumlahGelas);
        });

        mTambahPiring.setOnClickListener(v -> {
            tambahJumlah(mJumlahPiring);
        });
        mKurangPiring.setOnClickListener(v -> {
            kurangJumlah(mJumlahPiring);
        });

        mTambahMangkok.setOnClickListener(v -> {
            tambahJumlah(mJumlahMangkok);
        });
        mKurangMangkok.setOnClickListener(v -> {
            kurangJumlah(mJumlahMangkok);
        });

        mTukarButton.setOnClickListener(v -> {
            if(hadiahTidakSisa()) {
                List<PenukaranHadiah> listHadiah = extractHadiah();
                mViewModel.tukarHadiah(mPenukaran, listHadiah);
                DialogFragment dialog = TandaTanganDialog.newInstance(mPenukaran.getId());
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), DIALOG_TTD_TAG);
            }else {
                DialogFragment dialog = PesanDialog.newInstance("Penukaran Gagal\nJumlah Hadiah Tersisa");
                dialog.show(getSupportFragmentManager(), PesanDialog.PESAN_DIALOG_TAG);
            }
        });


    }

    private List<PenukaranHadiah> extractHadiah() {
        List<PenukaranHadiah> list = new ArrayList<>();
        if(Integer.parseInt(mJumlahGelas.getText().toString()) != 0) {
            int jumlahGelas = Integer.parseInt(mJumlahGelas.getText().toString());
            list.add(new PenukaranHadiah(mPenukaran.getId(), Hadiah.ID_GELAS, jumlahGelas));
        }
        if(Integer.parseInt(mJumlahPiring.getText().toString()) != 0) {
            int jumlahPiring = Integer.parseInt(mJumlahPiring.getText().toString());
            list.add(new PenukaranHadiah(mPenukaran.getId(), Hadiah.ID_PIRING, jumlahPiring));
        }
        if(Integer.parseInt(mJumlahMangkok.getText().toString()) != 0) {
            int jumlahMangkok = Integer.parseInt(mJumlahMangkok.getText().toString());
            list.add(new PenukaranHadiah(mPenukaran.getId(), Hadiah.ID_MANGKOK, jumlahMangkok));
        }
        return list;
    }

    private void initViews() {
        mTukarButton = findViewById(R.id.input_penukaran_hadiah_tukar_button);
        mNamaOutletText = findViewById(R.id.input_penukaran_hadiah_nama_outlet);
        mTotalHadiahText = findViewById(R.id.input_penukaran_hadiah_jumlah_hadiah);
        mTotalBungkusText = findViewById(R.id.input_penukaran_hadiah_jumlah_bungkus);
        mJumlahGelas = findViewById(R.id.input_penukaran_hadiah_jumlah_gelas);
        mJumlahMangkok = findViewById(R.id.input_penukaran_hadiah_jumlah_mangkok);
        mJumlahPiring = findViewById(R.id.input_penukaran_hadiah_jumlah_piring);
        mTambahGelas = findViewById(R.id.input_penukaran_hadiah_tambah_gelas);
        mTambahPiring = findViewById(R.id.input_penukaran_hadiah_tambah_piring);
        mTambahMangkok = findViewById(R.id.input_penukaran_hadiah_tambah_mangkok);
        mKurangGelas = findViewById(R.id.input_penukaran_hadiah_kurang_gelas);
        mKurangPiring = findViewById(R.id.input_penukaran_hadiah_kurang_piring);
        mKurangMangkok = findViewById(R.id.input_penukaran_hadiah_kurang_mangkok);
    }

    private void tampilkanSnackBar(String pesan) {
        Snackbar.make(mTotalBungkusText, pesan, Snackbar.LENGTH_SHORT).show();
    }

    private void tambahJumlah(TextView target) {
        if(bisaDiTambah()) {
            int nilai = Integer.parseInt(target.getText().toString());
            nilai++;
            target.setText(String.valueOf(nilai));
        }
    }

    private void kurangJumlah(TextView target) {
        int nilai = Integer.parseInt(target.getText().toString());
        if(nilai > 0) {
            nilai--;
            target.setText(String.valueOf(nilai));
        }
    }

    private boolean bisaDiTambah() {
        int jumlahGelas = Integer.parseInt(mJumlahGelas.getText().toString());
        int jumlahPiring = Integer.parseInt(mJumlahPiring.getText().toString());
        int jumlahMangkok = Integer.parseInt(mJumlahMangkok.getText().toString());
        int hasil = jumlahGelas + jumlahMangkok + jumlahPiring;
        if(hasil < mJumlahHadiah) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hadiahTidakSisa() {
        int jumlahGelas = Integer.parseInt(mJumlahGelas.getText().toString());
        int jumlahPiring = Integer.parseInt(mJumlahPiring.getText().toString());
        int jumlahMangkok = Integer.parseInt(mJumlahMangkok.getText().toString());
        int hasil = jumlahGelas + jumlahMangkok + jumlahPiring;
        if(hasil == mJumlahHadiah) {
            return true;
        } else {
            return false;
        }
    }

}
