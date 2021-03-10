package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.argdev.mypetslife.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PetInfoActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

    }
}