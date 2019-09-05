package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.KeluhanSaran;
import com.sps.monitoringsales.model.KeluhanSaranQuery;
import com.sps.monitoringsales.viewmodel.InputRatingActivityViewModel;

import java.util.List;

public class InputRatingActivity extends AppCompatActivity {


    private Button mSimpanButton;
    private RecyclerView mRecycleView;

    private InputRatingActivityViewModel mViewModel;

    private static final String KEY_ID = "InputRaingId";
    private int idPenilaian;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, InputRatingActivity.class);
        intent.putExtra(KEY_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rating);

        mSimpanButton = findViewById(R.id.input_rating_button);
        mRecycleView = findViewById(R.id.input_rating_recycleView);

        mRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        mViewModel = ViewModelProviders.of(this).get(InputRatingActivityViewModel.class);

        idPenilaian = getIntent().getIntExtra(KEY_ID, -1);
        if(idPenilaian != -1) {
            mViewModel.getKeluhanSaranQuery(idPenilaian).observe(this, keluhanSaranQueries -> {
                if(keluhanSaranQueries != null) {
                    if(keluhanSaranQueries.size() > 0) {
                        mRecycleView.setAdapter(new PenilaianProductAdapter(keluhanSaranQueries));
                    }
                }
            });
        }

        mSimpanButton.setOnClickListener(v -> {
            mViewModel.updateFullStatus(idPenilaian);
        });
    }





    class PenilaianProductHolder extends RecyclerView.ViewHolder  {

        private TextView mTextBungkus;
        private RatingBar mRatingBar;


        private KeluhanSaranQuery query;

        public PenilaianProductHolder(View itemView) {
            super(itemView);
            mTextBungkus = itemView.findViewById(R.id.list_rating_text);
            mRatingBar = itemView.findViewById(R.id.list_rating_ratingBar);
        }

        public void bindItem(KeluhanSaranQuery q) {
            query = q;
            mTextBungkus.setText(q.getNamaBungkus());

        }


    }


    class PenilaianProductAdapter extends RecyclerView.Adapter<PenilaianProductHolder> {

        private List<KeluhanSaranQuery> list;

        public PenilaianProductAdapter(List<KeluhanSaranQuery> list) {
            this.list = list;
        }

        @Override
        public PenilaianProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getBaseContext());
            PenilaianProductHolder holder = new PenilaianProductHolder(inflater.
                    inflate(R.layout.list_rating, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(PenilaianProductHolder holder, int position) {
            holder.bindItem(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
