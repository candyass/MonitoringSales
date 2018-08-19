package com.sps.monitoringsales.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.model.QueryTotalHadiah;
import com.sps.monitoringsales.viewmodel.MonitoringViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigit on 26/07/2018.
 */

public class MonitoringHadiahFragment extends Fragment {

    private PieChart mPieChart;
    private List<PieEntry> listEntry;
    private PieDataSet mPieDataSet;
    private PieData mPieData;
    private TextView mTextTotalHadiah;

    private int mTotal = 0;
    private MonitoringViewModel mViewModel;

    public static Fragment newInstance() {
        Fragment fragment = new MonitoringHadiahFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring_hadiah, container, false);
        mPieChart = view.findViewById(R.id.monitoring_hadiah_piechart);
        mTextTotalHadiah = view.findViewById(R.id.monitoring_hadiah_text_total_hadiah);
        mTextTotalHadiah.setText(String.valueOf(mTotal));

        listEntry = new ArrayList<>();
        int[] colors = {getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.color1),
                getResources().getColor(R.color.colorDarkTextLabel),getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),getResources().getColor(R.color.colorTextLabel)};

        mViewModel = ViewModelProviders.of(this).get(MonitoringViewModel.class);
        mViewModel.getQueryTotalHadiah().observe(this, query -> {
            if(query.size() > 0) {
                for(QueryTotalHadiah h : query) {
                    mTotal += h.getTotalHadiah();
                    if (h.getNamaHadiah().equalsIgnoreCase("Piring")) {
                        listEntry.add(new PieEntry(h.getTotalHadiah(), h.getNamaHadiah(),getResources()
                                .getDrawable(R.drawable.ic_piring_color)));
                    }else if(h.getNamaHadiah().equalsIgnoreCase("Gelas")) {
                        listEntry.add(new PieEntry(h.getTotalHadiah(), h.getNamaHadiah(),getResources()
                                .getDrawable(R.drawable.ic_gelas_color)));
                    }else {
                        listEntry.add(new PieEntry(h.getTotalHadiah(), h.getNamaHadiah(),getResources()
                                .getDrawable(R.drawable.ic_mangkok_color)));
                    }
                }
                mPieDataSet = new PieDataSet(listEntry,null);
                mPieDataSet.setSliceSpace(3f);
                mPieDataSet.setIconsOffset(new MPPointF(0, 40));
                mPieDataSet.setSelectionShift(5f);
                mPieDataSet.setColors(colors);
                mPieData = new PieData(mPieDataSet);
                mPieData.setValueTextColor(Color.WHITE);
                mPieChart.setData(mPieData);
                mPieChart.animateY(1000, Easing.EasingOption.EaseInBounce);
                mTextTotalHadiah.setText(String.valueOf(mTotal));
            }
        });

        Description desc = new Description();
        desc.setText("Daftar Hadiah");
        mPieChart.setDescription(desc);
        mPieChart.getLegend().setOrientation(Legend.LegendOrientation.HORIZONTAL);
        mPieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        mPieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);

        mPieChart.setCenterText("Presentase Penukaran Hadiah");
        mPieChart.setCenterTextColor(R.color.colorDarkTextLabel);
        mPieChart.setNoDataText("Tekan Untuk Melihat");


        return view;
    }
}
