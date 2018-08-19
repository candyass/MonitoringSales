package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.viewmodel.DetailOutletActivityViewModel;

public class DetailOutletActivity extends AppCompatActivity {

    private static final String EXTRA_OUTLET_ID = "detailoutletactivity.extra.outlet_id";

    private ImageView mCallButton;
    private TextView mNamaOutletText;
    private TextView mNoTeleponText;
    private ImageView mFotoOutlet;
    private TextView mAlamatText;
    private TextView mKategoriText;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, DetailOutletActivity.class);
        intent.putExtra(EXTRA_OUTLET_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_outlet);

        mFotoOutlet = findViewById(R.id.activity_detail_foto_outlet);
        mNamaOutletText = findViewById(R.id.activity_detail_nama_text);
        mNoTeleponText = findViewById(R.id.activity_detail_no_telepon_text);
        mAlamatText = findViewById(R.id.activity_detail_alamat_text);
        mKategoriText = findViewById(R.id.activity_detail_kategori_outlet_text);
        mCallButton = findViewById(R.id.activity_detail_call);

        int id = getIntent().getIntExtra(EXTRA_OUTLET_ID, -1);

        DetailOutletActivityViewModel viewModel = ViewModelProviders.of(this).get(DetailOutletActivityViewModel.class);

        if(id != -1) {
            viewModel.getOutlet(id).observe(this, outlet -> {
                mFotoOutlet.setImageBitmap(outlet.getFoto());
                mNamaOutletText.setText(outlet.getNamaOutlet());
                mNoTeleponText.setText(outlet.getNoTelepon());
                mAlamatText.setText(outlet.getLokasiOutlet().getAlamat());
                mKategoriText.setText(outlet.getKategoriOutlet());
            });
        }


    }
}
