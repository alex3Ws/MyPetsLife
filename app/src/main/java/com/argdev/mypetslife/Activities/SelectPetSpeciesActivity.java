package com.argdev.mypetslife.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.argdev.mypetslife.Adapters.RegisterPetGenericAdapter;
import com.argdev.mypetslife.Adapters.SelectAddPetAdapter;
import com.argdev.mypetslife.Entities.InfoMascotas;
import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectPetSpeciesActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<InfoMascotas> speciesList;
    public RecyclerView recyclerSpecies;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_species);

        user = (User)getIntent().getSerializableExtra("UserObject");

        recyclerSpecies = findViewById(R.id.rvPetSpecies);
        recyclerSpecies.setLayoutManager(new GridLayoutManager(this, 2));



        db = FirebaseFirestore.getInstance();


        db.collection("InfoMascotas")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    speciesList = new ArrayList<InfoMascotas>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        InfoMascotas infoMascotas =
                                new InfoMascotas(document.getId(),document.getString("IconoUrl"),document.getLong("Position").intValue());

                        speciesList.add(infoMascotas);
                    }

                    Collections.sort(speciesList);
                    RegisterPetGenericAdapter adapter = new RegisterPetGenericAdapter(speciesList,"ESP", user);
                    recyclerSpecies.setAdapter(adapter);

                }

            }
        });






    }



}