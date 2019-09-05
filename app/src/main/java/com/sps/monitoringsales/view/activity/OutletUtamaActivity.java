package com.sps.monitoringsales.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.view.fragment.DaftarSalesFragment;
import com.sps.monitoringsales.view.fragment.DetailOutletFragment;
import com.sps.monitoringsales.view.fragment.PenilaianKeluhanFragment;

public class OutletUtamaActivity extends AppCompatActivity {


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OutletUtamaActivity.class);
        return intent;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profil:
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_outlet_container);
                    if(fragment instanceof DetailOutletFragment) {

                    }else {
                        Fragment fragment1 = DetailOutletFragment.newInstance();
                        updateFragment(fragment1);
                    }
                    return true;
                case R.id.navigation_keluhan:
                    Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_outlet_container);
                    if(frag instanceof PenilaianKeluhanFragment) {

                    }else {
                        Fragment fragment1 = PenilaianKeluhanFragment.newInstance();
                        updateFragment(fragment1);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_utama);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Fragment fragment = DetailOutletFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_outlet_container, fragment).commit();
    }

    private void updateFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_outlet_container, fragment).commit();
    }

}
