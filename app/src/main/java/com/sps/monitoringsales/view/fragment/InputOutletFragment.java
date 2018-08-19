package com.sps.monitoringsales.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sps.monitoringsales.database.entity.Outlet;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.view.activity.InputActivity;
import com.sps.monitoringsales.viewmodel.InputActivityViewModel;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sigit on 15/04/2018.
 */

public class InputOutletFragment extends Fragment {

    private static final int REQUST_PHOTO  = 100;
    private boolean isPhotoSelected = false;
    private InputActivityViewModel mViewModel;

    private ImageButton mUploadButton;

    private CircleImageView mProfileImage;

    private Button mLanjutkanButton;

    private EditText mNamaPemilikText;
    private EditText mNamaOutletText;
    private EditText mNoTeleponText;

    private TextInputLayout mLayoutNamaPemilik, mLayoutNamaOutlet, mLayoutNoTelepon;
    private Uri mPhotoLocation;


    public static Fragment newInstance() {
        Fragment fragment = new InputOutletFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogger.logPesan("InputOutletFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyLogger.logPesan("InputOutletFragment onActivityCreated");
        InputActivity activity = (InputActivity) getActivity();
        mViewModel = activity.getViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.logPesan("InputOutletFragment onResume");
        if(isPhotoSelected && mViewModel.getFoto() != null) {
            MyLogger.logPesan("loading profile image from ViewModel");
            mProfileImage.setImageBitmap(mViewModel.getFoto());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MyLogger.logPesan("InputOutletFragment onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        MyLogger.logPesan("InputOutletFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        MyLogger.logPesan("InputOutletFragment onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLogger.logPesan("InputOutletFragment onDestroy");
        mViewModel = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_outlet, container, false);

        initializeWidgets(view);


        mUploadButton.setOnClickListener(v -> {
            mViewModel.reloadImage();
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            Intent choser = Intent.createChooser(intent, "Upload File Dari");
            startActivityForResult(choser, REQUST_PHOTO);
        });

        mLanjutkanButton.setOnClickListener(v -> {
            if(mPhotoLocation != null) {
                mViewModel.simpanFoto(mPhotoLocation);
            }
            if(isFieldValid()) {
                Outlet outlet = mViewModel.getOutlet();
                outlet.setNamaOutlet(mNamaOutletText.getText().toString());
                outlet.setNamaPemilik(mNamaPemilikText.getText().toString());
                outlet.setNoTelepon(mNoTeleponText.getText().toString());
                EventBus.getDefault().post(new InputEvent(true));
            }
        });

        return view;
    }

    private void initializeWidgets(View view) {
        mProfileImage = view.findViewById(R.id.fragment_input_profile_image);
        mUploadButton = view.findViewById(R.id.fragment_input_upload_button);
        mLanjutkanButton = view.findViewById(R.id.fragment_input_lanjutkan_button);
        mNamaOutletText = view.findViewById(R.id.fragment_input_nama_outlet_text);
        mNamaPemilikText = view.findViewById(R.id.fragment_input_nama_pemilik_text);
        mNoTeleponText = view.findViewById(R.id.fragment_input_no_telepon_text);
        mLayoutNamaOutlet = view.findViewById(R.id.fragment_input_nama_outlet_text_layout);
        mLayoutNamaPemilik = view.findViewById(R.id.fragment_input_nama_pemilik_text_layout);
        mLayoutNoTelepon = view.findViewById(R.id.fragment_input_no_telepon_text_layout);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUST_PHOTO) {
            if(resultCode == Activity.RESULT_OK) {
                Uri photoUri = data.getData();
                if(photoUri != null) {
                    Picasso.with(getContext()).load(photoUri).into(mProfileImage);
                    isPhotoSelected = true;
                    mPhotoLocation = photoUri;
                }
            }
        }
    }

    public boolean isFieldValid() {
        boolean isValidNamaPemilik, isValidNamaOutlet, isValidNoTelepon;
        if(mNamaPemilikText.getText().toString().isEmpty()) {
            mLayoutNamaPemilik.setError("Nama Pemilik Kosong");
            isValidNamaPemilik = false;
        } else {
            mLayoutNamaPemilik.setError(null);
            isValidNamaPemilik = true;
        }
        if(mNamaOutletText.getText().toString().isEmpty()) {
            mLayoutNamaOutlet.setError("Nama Outlet Kosong");
            isValidNamaOutlet = false;
        }else {
            mLayoutNamaOutlet.setError(null);
            isValidNamaOutlet = true;
        }
        if(mNoTeleponText.getText().toString().isEmpty()) {
            mLayoutNoTelepon.setError("No Telepon Kosong");
            isValidNoTelepon = false;
        }else {
            mLayoutNoTelepon.setError(null);
            isValidNoTelepon = true;
        }
        return isValidNamaOutlet && isValidNamaPemilik && isValidNoTelepon;
    }


    public static class InputEvent {
        private boolean isContinue;

        InputEvent(boolean isContinue) {
            this.isContinue = isContinue;
        }

        public void setContinue(boolean isContinue) {
            this.isContinue = isContinue;
        }

        public boolean isContinue() {
            return isContinue;
        }
    }
}
