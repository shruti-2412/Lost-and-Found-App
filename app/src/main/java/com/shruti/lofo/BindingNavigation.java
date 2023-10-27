package com.shruti.lofo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.shruti.lofo.databinding.ActivityBindNavBinding;
import com.shruti.lofo.ui.DashBoard.DashBoardFragment;
import com.shruti.lofo.ui.Found.FoundFragment;
import com.shruti.lofo.ui.Help.HelpFragment;
import com.shruti.lofo.ui.Lost.LostFragment;


public class BindingNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerlayout;
    BottomNavigationView bottomNavigationView;  // Change this to BottomNavigationView
    FragmentManager fragmentManager;
    Toolbar toolbar;
    private ActivityBindNavBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_nav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerlayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        // Fix the ID for your BottomNavigationView
        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_dashboard) {
                    openFragment(new DashBoardFragment());
                    return true;
                } else if (itemId == R.id.navigation_lost) {
                    openFragment(new LostFragment());
                    return true;
                } else if (itemId == R.id.navigation_found) {
                    openFragment(new FoundFragment());
                    return true;
                } else if (itemId == R.id.navigation_help) {
                    openFragment(new HelpFragment());
                    return true;
                }
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new DashBoardFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // Handle drawer item selection here
//        if (itemId == R.id.drawer_item1) {
//            // Perform action for drawer item 1
//        } else if (itemId == R.id.drawer_item2) {
//            // Perform action for drawer item 2
//        }

        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}

