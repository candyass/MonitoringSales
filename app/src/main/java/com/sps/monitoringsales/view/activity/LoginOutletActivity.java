package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.dialog.PesanDialog;
import com.sps.monitoringsales.viewmodel.LoginOutletActivityViewModel;

public class LoginOutletActivity extends AppCompatActivity {

    private EditText mNamaPemilikText;
    private EditText mNoTeleponText;
    private Button mLoginButton;
    private Button mLoginSalesButton;

    LoginOutletActivityViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_outlet);

        mNamaPemilikText = findViewById(R.id.activity_login_sales_id1);
        mNoTeleponText = findViewById(R.id.activity_login_password1);
        mLoginButton = findViewById(R.id.activity_login_login_button1);
        mLoginSalesButton = findViewById(R.id.button_login_sales);

        mViewModel = ViewModelProviders.of(this).get(LoginOutletActivityViewModel.class);

        mLoginButton.setOnClickListener(v -> {
            MyLogger.logPesan("login click");
            if(mNamaPemilikText.getText().toString().isEmpty() | mNoTeleponText.getText().toString().isEmpty()) {
                DialogFragment dialog = PesanDialog.newInstance("Field Harus Diisi");
                dialog.show(getSupportFragmentManager(), PesanDialog.PESAN_DIALOG_TAG);
            }
            else {
                String namaPemilik = mNamaPemilikText.getText().toString();
                String noTelepon = mNoTeleponText.getText().toString();
                Outlet outlet = mViewModel.getOutletLogin(namaPemilik, noTelepon);
                if(outlet != null) {
                    MyLogger.logPesan("outlet is not null");
                    Intent intent = OutletUtamaActivity.newIntent(getBaseContext());
                    startActivity(intent);
                    finish();
                } else {
                    DialogFragment dialog = PesanDialog.newInstance("Login Gagal");
                    dialog.show(getSupportFragmentManager(), PesanDialog.PESAN_DIALOG_TAG);
                }
            }
        });

        mLoginSalesButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.newIntent(getBaseContext());
            startActivity(intent);
            finish();
        });
    }
}
