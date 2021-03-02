package com.argdev.mypetslife.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.argdev.mypetslife.Adapters.RegisterPetGenericAdapter;
import com.argdev.mypetslife.Adapters.SelectAddPetAdapter;
import com.argdev.mypetslife.Entities.InfoMascotas;
import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectBreedActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<InfoMascotas> breedList;
    public RecyclerView recyclerBreed;
    private User user;
    private Mascotas addMascota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_breed);


        user = (User)getIntent().getSerializableExtra("UserObject");
        addMascota = (Mascotas)getIntent().getSerializableExtra("MascotaObject");

        recyclerBreed = findViewById(R.id.rvPetBreeds);
        recyclerBreed.setLayoutManager(new GridLayoutManager(this, 2));

        db = FirebaseFirestore.getInstance();


        db.collection("InfoMascotas").document(addMascota.getEspecie()).collection("Razas")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    breedList = new ArrayList<InfoMascotas>();


                    for (QueryDocumentSnapshot document : task.getResult()) {
                        InfoMascotas infoMascotas =
                                new InfoMascotas(document.getId(),document.getString("ImgUrl"),document.getLong("Position").intValue());

                         breedList.add(infoMascotas);
                    }

                    Collections.sort(breedList);
                    RegisterPetGenericAdapter adapter = new RegisterPetGenericAdapter(breedList,"BREED", user,addMascota);
                    recyclerBreed.setAdapter(adapter);

                }

            }
        });



    }

}