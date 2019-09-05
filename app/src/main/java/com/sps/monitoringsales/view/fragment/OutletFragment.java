package com.sps.monitoringsales.view.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.view.activity.DetailOutletActivity;
import com.sps.monitoringsales.view.activity.InputActivity;
import com.sps.monitoringsales.view.activity.UtamaActivity;
import com.sps.monitoringsales.viewmodel.OutletFragmentViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 14/03/2018.
 */

public class OutletFragment extends Fragment {

    private static final int REQUEST_INPUT_OUTLET = 100;
    private static final String KEY_AKUN_ID = "com.monitoringsales.outletfragment.key.akunid";


    private RecyclerView mRecyclerView;
    private ViewSwitcher mSwitcher;
    private TextView mLabelText;
    private OutletAdapter mAdapter;
    private OutletFragmentViewModel mViewModel;
    private String mIdAkun;



    public static Fragment newInstance(String akunId) {
        Fragment fragment = new OutletFragment();
        Bundle arg = new Bundle();
        arg.putString(KEY_AKUN_ID, akunId);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OutletFragmentViewModel.class);
        MyLogger.logPesan("OutletFragment onCreate is called");
        mIdAkun = getArguments().getString(KEY_AKUN_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_layout_stub, container, false);
        mSwitcher = v.findViewById(R.id.list_layout_switcher);
        mRecyclerView = v.findViewById(R.id.list_layout_recyclerView);
        mLabelText = v.findViewById(R.id.list_layout_textView);

        mLabelText.setText(R.string.list_outlet_label);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getListOutlet(mIdAkun).observe(this, list -> {
            if(list != null) {
                mRecyclerView.setAdapter(new OutletAdapter(list));
                if(list.size() > 0) {
                    showListItem(true);
                }
            }
        });
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyLogger.logPesan("OutletFragment onActivityCreated");
        UtamaActivity activity = (UtamaActivity) getActivity();
        activity.setTitleAndAction(R.string.label_daftar_outlet, v -> {
            Intent intent = InputActivity.newIntent(getContext());
            startActivityForResult(intent, REQUEST_INPUT_OUTLET);
        }, true);
        activity.setMenuOutletNavigation();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_INPUT_OUTLET && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                tampilkanSnackBar(data.getLongExtra(InputLokasiFragment.EXTRA_OUTLET_ID, -1));
            }
        }
    }

    private void tampilkanSnackBar(long outletId) {
        Snackbar bar = Snackbar.make(getView(), R.string.label_input_outlet_berhasil, Snackbar.LENGTH_LONG);
        bar.setAction("Lihat", v -> {
            Intent intent = DetailOutletActivity.newIntent(getContext(), (int) outletId);
            startActivity(intent);
        });
        bar.show();
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

    class OutletHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textNama;
        private TextView textAlamat;
        private TextView textJenis;
        private CircleImageView imageView;
        private Outlet outlet;

        public OutletHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textAlamat = itemView.findViewById(R.id.list_outlet_textAlamat);
            textJenis = itemView.findViewById(R.id.list_outlet_textJenis);
            textNama = itemView.findViewById(R.id.list_outlet_textNama);
            imageView = itemView.findViewById(R.id.list_outlet_foto);
        }

        public void bindItem(Outlet o) {
            outlet = o;
            textNama.setText(outlet.getNamaPemilik());
            textAlamat.setText(outlet.getNamaOutlet());
            textJenis.setText(outlet.getJenisOutlet());
            if(outlet.getFoto() != null) {
                imageView.setImageBitmap(outlet.getFoto());
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailOutletActivity.newIntent(getContext(), outlet.getId());
            startActivity(intent);
        }


    }

    class OutletAdapter extends RecyclerView.Adapter<OutletHolder> {


        private List<Outlet> list;

        public OutletAdapter(List<Outlet> list) {
            this.list = list;
        }

        @Override
        public OutletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.list_outlet, parent, false);
            OutletHolder holder = new OutletHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(OutletHolder holder, int position) {
            holder.bindItem(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
