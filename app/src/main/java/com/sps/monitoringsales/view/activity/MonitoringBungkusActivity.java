package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.model.QueryTotalBungkus;
import com.sps.monitoringsales.viewmodel.MonitoringViewModel;

import java.util.ArrayList;
import java.util.List;

public class MonitoringBungkusActivity extends AppCompatActivity {

    private static final String EXTRA_AKUN_ID = "com.monitoringsales.monitoringbungkus.extra.akunid";

    private PieChart mPieChart;
    private List<PieEntry> listEntry;
    private PieDataSet mPieDataSet;
    private PieData mPieData;
    private MonitoringViewModel mViewModel;
    private String mAkunId;


    public static Intent newIntent(Context context, String idAkun) {
        Intent intent = new Intent(context, MonitoringBungkusActivity.class);
        intent.putExtra(EXTRA_AKUN_ID, idAkun);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_monitoring_bungkus);
        mAkunId = getIntent().getStringExtra(EXTRA_AKUN_ID);


        mPieChart = findViewById(R.id.monitoring_bungkus_piechart);

        listEntry = new ArrayList<>();
        int[] colors = {getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.color1),
                getResources().getColor(R.color.colorDarkTextLabel),getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),getResources().getColor(R.color.colorTextLabel)};


        mViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);
        mViewModel.getQueryTotalBungkus(mAkunId).observe(this, query -> {
            if(query.size() > 0) {
                for(QueryTotalBungkus b : query) {
                    listEntry.add(new PieEntry(b.getTotalBungkus(),b.getNamaBungkus()));
                }
                mPieDataSet = new PieDataSet(listEntry,null);
                mPieDataSet.setSliceSpace(3f);
                mPieDataSet.setSelectionShift(5f);
                mPieDataSet.setColors(colors);
                mPieData = new PieData(mPieDataSet);
                mPieData.setValueTextColor(Color.WHITE);
                mPieChart.setData(mPieData);
                mPieChart.setDrawHoleEnabled(false);
                mPieChart.setDrawCenterText(false);
                mPieChart.animateY(1000, Easing.EasingOption.EaseInBounce);
            }
        });

        Description desc = new Description();
        desc.setText("Daftar Produk");
        mPieChart.setDescription(desc);
        mPieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        mPieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mPieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        mPieChart.setCenterText("Presentase Penukaran Bungkus");
        mPieChart.setCenterTextColor(R.color.colorDarkTextLabel);
        mPieChart.setNoDataText("Tekan Untuk Melihat");

    }
}
