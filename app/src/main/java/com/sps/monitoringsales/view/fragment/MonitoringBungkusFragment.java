package com.sps.monitoringsales.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.model.QueryTotalBungkus;
import com.sps.monitoringsales.viewmodel.MonitoringViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigit on 26/07/2018.
 */

public class MonitoringBungkusFragment extends Fragment {

    private PieChart mPieChart;
    private List<PieEntry> listEntry;
    private PieDataSet mPieDataSet;
    private PieData mPieData;
    private MonitoringViewModel mViewModel;


    public static Fragment newInstance() {
        Fragment fragment = new MonitoringBungkusFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring_bungkus, container, false);
        mPieChart = view.findViewById(R.id.monitoring_bungkus_piechart);

        listEntry = new ArrayList<>();
        int[] colors = {getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.color1),
        getResources().getColor(R.color.colorDarkTextLabel),getResources().getColor(R.color.color2),
        getResources().getColor(R.color.color3),getResources().getColor(R.color.colorTextLabel)};


        mViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);
        mViewModel.getQueryTotalBungkus().observe(this, query -> {
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


        return view;
    }
}
