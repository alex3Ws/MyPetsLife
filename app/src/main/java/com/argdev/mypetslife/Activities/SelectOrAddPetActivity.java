package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

public class SelectOrAddPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_or_add_pet);

        User user = (User)getIntent().getSerializableExtra("UserObject");


        Toast.makeText(getApplicationContext(),"Prueba:" +user.toStringF(), Toast.LENGTH_SHORT).show();

    }
}