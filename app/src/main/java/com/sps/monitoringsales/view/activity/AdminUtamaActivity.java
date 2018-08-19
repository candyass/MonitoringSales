package com.sps.monitoringsales.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sps.monitoringsales.R;
import com.sps.monitoringsales.database.entity.Akun;
import com.sps.monitoringsales.view.fragment.MonitoringBungkusFragment;
import com.sps.monitoringsales.view.fragment.MonitoringHadiahFragment;
import com.sps.monitoringsales.viewmodel.UtamaActivityViewModel;

public class AdminUtamaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private UtamaActivityViewModel mViewModel;
    private NavigationView mNavigationView;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AdminUtamaActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mViewModel = ViewModelProviders.of(this).get(UtamaActivityViewModel.class);
        initializeHeader();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.admin_fragment_container);
        if(fragment == null) {
            fragment = MonitoringBungkusFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.admin_fragment_container, fragment).commit();
            mNavigationView.setCheckedItem(R.id.nav_monitor_bungkus);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_utama, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.admin_fragment_container);
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_monitor_bungkus:
                if(fragment instanceof MonitoringBungkusFragment) {

                }else {
                    changeFragment(MonitoringBungkusFragment.newInstance());
                    mNavigationView.setCheckedItem(R.id.nav_monitor_bungkus);
                }
                break;
            case R.id.nav_monitor_hadiah:
                if(fragment instanceof MonitoringHadiahFragment) {

                }else {
                    changeFragment(MonitoringHadiahFragment.newInstance());
                    mNavigationView.setCheckedItem(R.id.nav_monitor_hadiah);
                }
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initializeHeader() {
        View headerView = mNavigationView.getHeaderView(0);
        TextView headerTextNama = headerView.findViewById(R.id.nav_header_nama);
        TextView headerTextDeskripsi = headerView.findViewById(R.id.nav_header_deskripsi);

        Akun akun = mViewModel.getAkun();
        headerTextNama.setText(akun.getNamaPengguna());
        headerTextDeskripsi.setText("Admin");
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container, fragment).commit();
    }
}
