package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.argdev.mypetslife.Adapters.SelectAddPetAdapter;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

public class SelectOrAddPetActivity extends AppCompatActivity {

    private User user;
    RecyclerView recyclerMascotas;
    ImageView addPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_or_add_pet);

        user = (User)getIntent().getSerializableExtra("UserObject");

        recyclerMascotas = findViewById(R.id.rvPets);
        addPet = findViewById(R.id.ivAddPet);

        addPet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent createPet = new Intent(v.getContext(), SelectPetSpeciesActivity.class);
                createPet.putExtra("UserObject",user);
                v.getContext().startActivity(createPet);
            }

        });


        if(user.getMascotas().size() != 0){

            addPet.setVisibility(View.GONE);


            recyclerMascotas.setVisibility(View.VISIBLE);
            recyclerMascotas.setLayoutManager(new LinearLayoutManager(this));



            SelectAddPetAdapter adapter = new SelectAddPetAdapter(user.getMascotas(),user);
            recyclerMascotas.setAdapter(adapter);

        }
        else{


        }




        //Toast.makeText(getApplicationContext(),"Prueba:" +user.toStringF(), Toast.LENGTH_SHORT).show();

    }
}