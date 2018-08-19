package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.view.fragment.InputLokasiFragment;
import com.sps.monitoringsales.view.fragment.InputOutletFragment;
import com.sps.monitoringsales.viewmodel.InputActivityViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class InputActivity extends AppCompatActivity {

    private InputActivityViewModel mViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, InputActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().hide();
        mViewModel = ViewModelProviders.of(this).get(InputActivityViewModel.class);

        FragmentManager fm =  getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_input_container);
        if(fragment == null) {
            fragment = InputOutletFragment.newInstance();
            fm.beginTransaction().add(R.id.activity_input_container, fragment).commit();
        }

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

    private void loadInputLokasi() {
        Fragment fragment = InputLokasiFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_input_container, fragment).addToBackStack(null).commit();
    }

    public InputActivityViewModel getViewModel() {
        return mViewModel;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InputOutletFragment.InputEvent event) {
        if(event.isContinue()) {
            loadInputLokasi();
        }
    }
}
