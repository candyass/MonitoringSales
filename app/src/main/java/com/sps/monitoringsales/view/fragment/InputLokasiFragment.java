package com.sps.monitoringsales.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sps.monitoringsales.database.entity.LokasiOutlet;
import com.sps.monitoringsales.util.MyLogger;
import com.sps.monitoringsales.R;
import com.sps.monitoringsales.view.activity.InputActivity;
import com.sps.monitoringsales.viewmodel.InputActivityViewModel;

/**
 * Created by sigit on 15/04/2018.
 */

public class InputLokasiFragment extends Fragment implements OnMapReadyCallback {


    private static final int REQUEST_LOKASI = 100;
    public static final String EXTRA_OUTLET_ID = "com.sps.monitoringsales.extra.outlet_id";

    private FloatingActionButton mLokasiButton;
    private TextView mAlamatTeks;
    private Spinner spinner;
    private Button  mSimpanButton;
    private SupportMapFragment mapFragment;
    private InputActivityViewModel mViewModel;

    private boolean isLocationSelected = false;
    private LokasiOutlet mLokasiOutlet;
    private Place mPlace;



    public static Fragment newInstance() {
        Fragment fragment = new InputLokasiFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLogger.logPesan("InputLokasiFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyLogger.logPesan("InputLokasiFragment onActivityCreated");
        InputActivity activity = (InputActivity) getActivity();
        mViewModel = activity.getViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapFragment.getMapAsync(this);
        MyLogger.logPesan("InputLokasiFragment onResume");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLogger.logPesan("InputLokasiFragment onDestroy");
        mViewModel = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_lokasi, container, false);


        mLokasiButton = view.findViewById(R.id.fragment_input_lokasi_fab);
        mAlamatTeks = view.findViewById(R.id.fragment_input_lokasi_lokasiText);
        spinner  = view.findViewById(R.id.fragment_input_lokasi_spinner);
        mSimpanButton = view.findViewById(R.id.fragment_input_lokasi_simpan_button);

        String[] arrayString = getResources().getStringArray(R.array.jenis_outlet);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, arrayString);
        spinner.setAdapter(stringArrayAdapter);



        mLokasiButton.setOnClickListener(v -> {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(getActivity()), REQUEST_LOKASI);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        });

        mSimpanButton.setOnClickListener(v -> {
            if(mPlace == null) {
                tampilkanSnackbar("Lokasi Outlet Belum Ditentukan");
            } else {
                LatLng latLng = mPlace.getLatLng();
                mLokasiOutlet = new LokasiOutlet(mPlace.getAddress().toString(), latLng.latitude, latLng.longitude);
                mViewModel.getOutlet().setLokasiOutlet(mLokasiOutlet);
                mViewModel.getOutlet().setKategoriOutlet((String)spinner.getSelectedItem());
                long id = mViewModel.simpanOutlet();
                if(id > 0) {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_OUTLET_ID, id);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                }
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_input_lokasi_mapFragment);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("kopi", "onActivityResult is called");
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_LOKASI) {
                mPlace = PlacePicker.getPlace(getContext(), data);
                if(mPlace != null) {
                    mAlamatTeks.setText(mPlace.getAddress());
                    isLocationSelected = true;
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MyLogger.logPesan("onMapReady is called");
        if(isLocationSelected) {
            CameraUpdate centerLocation = CameraUpdateFactory.newLatLng(mPlace.getLatLng());
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);
            googleMap.addMarker(new MarkerOptions().position(mPlace.getLatLng()));
            googleMap.moveCamera(centerLocation);
            googleMap.animateCamera(zoom);
        }else {
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng( new LatLng(40.76793169992044,
                            -73.98180484771729));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
        }
    }


    private void tampilkanSnackbar(String pesan) {
        Snackbar.make(getView(), pesan, Snackbar.LENGTH_SHORT).show();
    }


}
