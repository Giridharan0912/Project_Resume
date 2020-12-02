package com.alphaverse.projectresume.activities.profilelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.profilelist.profilelistfragments.ProfileListFragment;
import com.google.android.material.navigation.NavigationView;

public class ProfileListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  private   DrawerLayout drawerLayout;
  private   NavigationView navigationView;
 private    FragmentManager fragmentManager;
 private FragmentTransaction fragmentTransaction;
  private   ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.setStatusBarBackgroundColor(Color.WHITE);
        actionBarDrawerToggle.syncState();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        ProfileListFragment profileListFragment=new ProfileListFragment();
        fragmentTransaction.replace(R.id.frame,profileListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);



    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_sample_resumes:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_rate_us:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.application_name);

    }
}