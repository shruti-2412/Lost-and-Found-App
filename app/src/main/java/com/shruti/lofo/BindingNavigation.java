package com.shruti.lofo;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.FirebaseApp;
import com.shruti.lofo.databinding.ActivityBindNavBinding;


public class BindingNavigation extends AppCompatActivity {


    private ActivityBindNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(BindingNavigation.this);
        super.onCreate(savedInstanceState);

        binding = ActivityBindNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE); // Set the visibility to be visible

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_lost, R.id.navigation_found, R.id.navigation_help
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dash_board);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
}
