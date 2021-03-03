package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

public class AddPetPhotoActivity extends AppCompatActivity {

    TextView textView2;

    User user;
    Mascotas addMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_photo);

        user = (User)getIntent().getSerializableExtra("UserObject");
        addMascota = (Mascotas)getIntent().getSerializableExtra("MascotaObject");

        textView2 = findViewById(R.id.textView2);

        textView2.setText(addMascota.toString());




    }
}