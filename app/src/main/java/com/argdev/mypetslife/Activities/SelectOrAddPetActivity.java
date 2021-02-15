package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.argdev.mypetslife.Adapters.SelectAddPetAdapter;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

public class SelectOrAddPetActivity extends AppCompatActivity {

    private User user;
    public RecyclerView recyclerMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_or_add_pet);

        user = (User)getIntent().getSerializableExtra("UserObject");

        recyclerMascotas = findViewById(R.id.rvPets);
        recyclerMascotas.setLayoutManager(new LinearLayoutManager(this));

        SelectAddPetAdapter adapter = new SelectAddPetAdapter(user.getMascotas());
        recyclerMascotas.setAdapter(adapter);




        //Toast.makeText(getApplicationContext(),"Prueba:" +user.toStringF(), Toast.LENGTH_SHORT).show();

    }
}