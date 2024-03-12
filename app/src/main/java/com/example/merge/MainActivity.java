package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.merge.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        binding.bottomNavView.setOnItemSelectedListener(itemId -> {
            if (itemId.getItemId() == R.id.bottom_home) {
                repFragment(new HomeFragment());
            } else if (itemId.getItemId() == R.id.bottom_profile) {
                repFragment(new ProfileFragment());
            } else if (itemId.getItemId() == R.id.bottom_settings) {
                repFragment(new SettingsFragment());
            }
            return true;

        });

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(itemId -> {
            int item = itemId.getItemId();
            if (item == R.id.nav_home) {
                repFragment(new HomeFragment());
            } else if (item == R.id.nav_profile) {
                repFragment(new ProfileFragment());
            } else if (item == R.id.nav_settings) {
                repFragment(new SettingsFragment());
            } else if(item == R.id.nav_msg){
                repFragment(new MessageFragment());
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
}
    private void repFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_out,fragment);
        fragmentTransaction.commit();
    }




}





