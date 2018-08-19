package com.sps.monitoringsales.view.dialog;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.model.SelectedBungkus;
import com.sps.monitoringsales.database.entity.Bungkus;
import com.sps.monitoringsales.viewmodel.ListBungkusDialogViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by sigit on 19/04/2018.
 */

public class ListBungkusDialog extends DialogFragment {



    private ListBungkusDialogViewModel mViewModel;

    public static DialogFragment newInstance() {
        DialogFragment dialog = new ListBungkusDialog();
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListBungkusDialogViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.dialog_list_bungkus, null);
        RecyclerView recyclerView = v.findViewById(R.id.dialog_list_bungkus_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mViewModel.getListBungkus().observe(this, bungkuses -> {
            recyclerView.setAdapter(new ListBungkusAdapter(bungkuses));
        });
        builder.setView(v);
        builder.setTitle("Pilih Bungkus Untuk Ditukar");
        builder.setIcon(R.drawable.spslogo);


        builder.setNegativeButton("Batal", null);
        return builder.create();
    }


    class ListBungkusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView gambarBungkus;
        private TextView namaBungkus;
        private ImageView checkImage;
        private Button button;

        private Bungkus bungkus;


        public ListBungkusHolder(View itemView) {
            super(itemView);
            gambarBungkus = itemView.findViewById(R.id.list_bungkus_gambar_bungkus);
            namaBungkus = itemView.findViewById(R.id.list_bungkus_nama_bungkus);
            button = itemView.findViewById(R.id.list_bungkus_tukar_button);
            checkImage = itemView.findViewById(R.id.list_bungkus_check_image);

            button.setOnClickListener(v -> {
                SelectedBungkus selectedBungkus = new SelectedBungkus(bungkus.getIdBungkus(), bungkus.getNamaBungkus(), 0);
                EventBus.getDefault().post(selectedBungkus);
                dismiss();
            });
        }

        public void bindItem(Bungkus bungkus) {
            this.bungkus = bungkus;
            gambarBungkus.setImageDrawable(getResources().getDrawable(bungkus.getGambarBungkus()));
            namaBungkus.setText(bungkus.getNamaBungkus());
        }

        @Override
        public void onClick(View v) {
            checkImage.setVisibility(View.VISIBLE);
        }
    }


    class ListBungkusAdapter extends RecyclerView.Adapter<ListBungkusHolder> {


        private List<Bungkus> list;

        public ListBungkusAdapter(List<Bungkus> list) {
            this.list = list;
        }

        @Override
        public ListBungkusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.list_bungkus, parent, false);
            ListBungkusHolder holder = new ListBungkusHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ListBungkusHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
