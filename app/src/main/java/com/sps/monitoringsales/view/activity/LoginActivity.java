package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.view.dialog.PesanDialog;
import com.sps.monitoringsales.viewmodel.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {


    private EditText mSalesIdText;
    private EditText mPasswordText;
    private Button mLoginButton;

    private LoginActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyLogger.logPesan("Login onCreate");

        mSalesIdText = findViewById(R.id.activity_login_sales_id);
        mPasswordText = findViewById(R.id.activity_login_password);
        mLoginButton = findViewById(R.id.activity_login_login_button);


        mViewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);


        mLoginButton.setOnClickListener(v -> {
            if(inputKosong()) {
                DialogFragment dialog = PesanDialog.newInstance("Field Harus Diisi");
                dialog.show(getSupportFragmentManager(), PesanDialog.PESAN_DIALOG_TAG);
            }else {
                String salesId = mSalesIdText.getText().toString();
                String password = mPasswordText.getText().toString();
                Akun akun = mViewModel.getAkunLogin(salesId, password);
                if(akun == null) {
                    DialogFragment dialog = PesanDialog.newInstance("Login Gagal\nID Dan Password Salah !");
                    dialog.show(getSupportFragmentManager(), PesanDialog.PESAN_DIALOG_TAG);
                }else {
                    if (akun.getLoginSebagai() == Akun.LOGIN_SALES) {
                        Intent intent = UtamaActivity.newIntent(getBaseContext());
                        finish();
                        startActivity(intent);
                    }else {
                        Intent intent = AdminUtamaActivity.newIntent(getBaseContext());
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLogger.logPesan("Login onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLogger.logPesan("Login onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLogger.logPesan("Login onDestroy");
    }

    private boolean inputKosong() {
        if (mSalesIdText.getText().toString().isEmpty() || mPasswordText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }
}
