package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.view.fragment.OutletFragment;
import com.sps.monitoringsales.view.fragment.PenukaranBungkusFragment;
import com.sps.monitoringsales.view.fragment.PenukaranHadiahFragment;
import com.sps.monitoringsales.viewmodel.UtamaActivityViewModel;

public class UtamaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private NavigationView mNavigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private ActionBarDrawerToggle toggle;
    private CoordinatorLayout coordinatorLayout;

    private FragmentManager fm;
    private UtamaActivityViewModel mViewModel;

    private boolean isNavigated = false;
    private static String BACKSTACK_MAIN = "main";
    private static String KEY_ISNAVIGATED = "com.sps.monitoringsales.key.isNavigated";


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UtamaActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        mViewModel = ViewModelProviders.of(this).get(UtamaActivityViewModel.class);
        initializeViews();
        initializeHeader();




        fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = OutletFragment.newInstance();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(isNavigated) {
                if (fm.findFragmentById(R.id.fragment_container) instanceof  OutletFragment) {
                    Toast.makeText(getBaseContext(), "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
                }
                fm.popBackStack(BACKSTACK_MAIN, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                isNavigated = false;
                return;
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ISNAVIGATED, isNavigated);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isNavigated = savedInstanceState.getBoolean(KEY_ISNAVIGATED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        Intent t = null;
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_outlet:
                if (f instanceof OutletFragment) {

                } else {
                    changeFragment(fm, OutletFragment.newInstance());
                    enableNavigated();
                }
                break;
            case R.id.nav_penukaran_bungkus:
                if (f instanceof PenukaranBungkusFragment) {

                } else {
                    changeFragment(fm, PenukaranBungkusFragment.newInstance());
                    enableNavigated();
                }
                break;
            case R.id.nav_penukaran_hadiah:
                if (f instanceof PenukaranHadiahFragment) {

                }else {
                    changeFragment(fm, PenukaranHadiahFragment.newInstance());
                    enableNavigated();
                }
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ViewGroup getParentWidget() {
        return coordinatorLayout;
    }

    public void setTitleAndAction(int resId, View.OnClickListener listener, boolean enable) {
        if(enable) {
            fab.setVisibility(View.VISIBLE);
        }else {
            fab.setVisibility(View.INVISIBLE);
        }
        getSupportActionBar().setTitle(resId);
        fab.setOnClickListener(listener);
    }

    public void setMenuOutletNavigation() {
        mNavigationView.setCheckedItem(R.id.nav_outlet);
    }

    private void enableNavigated() {
        isNavigated = true;
    }

    private void initializeViews() {
        mNavigationView = findViewById(R.id.nav_view);


        coordinatorLayout = findViewById(R.id.cordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void changeFragment(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(BACKSTACK_MAIN).commit();
    }

    private void initializeHeader() {
        View headerView = mNavigationView.getHeaderView(0);
        TextView headerTextNama = headerView.findViewById(R.id.nav_header_nama);
        TextView headerTextDeskripsi = headerView.findViewById(R.id.nav_header_deskripsi);

        Akun akun = mViewModel.getAkun();
        headerTextNama.setText(akun.getNamaPengguna());
        headerTextDeskripsi.setText("Sales");
    }




}
