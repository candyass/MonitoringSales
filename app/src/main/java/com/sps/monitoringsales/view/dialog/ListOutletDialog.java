package com.sps.monitoringsales.view.dialog;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.model.OutletEvent;
import com.sps.monitoringsales.viewmodel.ListOutletDialogViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 27/04/2018.
 */

public class ListOutletDialog extends DialogFragment {

    private static final String KEY_AKUN_ID = "com.monitoringsales.dialog.key.akunid";


    private RecyclerView mRecyclerView;
    private TextView mTextLabel;
    private ViewSwitcher mSwitcher;
    private String mAkunId;

    public static DialogFragment newInstance(String akunId) {
        DialogFragment dialog = new ListOutletDialog();
        Bundle arg = new Bundle();
        arg.putString(KEY_AKUN_ID, akunId);
        dialog.setArguments(arg);
        return dialog;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mAkunId = getArguments().getString(KEY_AKUN_ID);
        ListOutletDialogViewModel viewModel = ViewModelProviders.of(this).get(ListOutletDialogViewModel.class);

        View v = inflater.inflate(R.layout.list_layout_stub, null);
        mSwitcher = v.findViewById(R.id.list_layout_switcher);
        mRecyclerView = v.findViewById(R.id.list_layout_recyclerView);
        mTextLabel = v.findViewById(R.id.list_layout_textView);

        mTextLabel.setText("Tidak ada outlet untuk dipilih");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllOutlet(mAkunId).observe(this, list -> {
            if(list != null) {
                if(list.size() > 0) {
                    mRecyclerView.setAdapter(new OutletAdapter(list));
                    mSwitcher.showNext();
                }
            }
        });



        builder.setTitle("Daftar Outlet");
        builder.setMessage("Pilih Outlet");
        builder.setIcon(R.drawable.spslogo);
        builder.setView(v);
        builder.setNegativeButton("Batal", null);
        return builder.create();
    }

    class OutletHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView mFotoOutlet;
        private TextView mTextNamaPemilik;
        private TextView mTextJenis;
        private TextView mTextNamaOutlet;

        private Outlet outlet;

        public OutletHolder(View itemView) {
            super(itemView);
            mFotoOutlet = itemView.findViewById(R.id.list_outlet_foto);
            mTextNamaOutlet = itemView.findViewById(R.id.list_outlet_textNama);
            mTextJenis = itemView.findViewById(R.id.list_outlet_textJenis);
            mTextNamaPemilik = itemView.findViewById(R.id.list_outlet_textAlamat);
            itemView.setOnClickListener(this);
        }

        public void bindItem(Outlet outlet) {
            mFotoOutlet.setImageBitmap(outlet.getFoto());
            mTextNamaPemilik.setText(outlet.getNamaPemilik());
            mTextNamaOutlet.setText(outlet.getNamaOutlet());
            mTextJenis.setText(outlet.getJenisOutlet());
            this.outlet = outlet;
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new OutletEvent(outlet.getId(),outlet.getFoto(), outlet.getNamaOutlet()));
            dismiss();
        }
    }

    class OutletAdapter extends RecyclerView.Adapter<OutletHolder> {

        private List<Outlet> mListOutlet;


        public OutletAdapter(List<Outlet> mListOutlet) {
            this.mListOutlet = mListOutlet;
        }

        @Override
        public OutletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.list_outlet, parent, false);
            OutletHolder holder = new OutletHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(OutletHolder holder, int position) {
            holder.bindItem(mListOutlet.get(position));
        }

        @Override
        public int getItemCount() {
            return mListOutlet.size();
        }
    }
}
