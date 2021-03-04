package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

import java.util.ArrayList;
import java.util.List;

public class SelectWeightActivity extends AppCompatActivity {


    NumberPicker npKilos,npGramos;
    ImageButton btnNext;

    int kilos, gramos;

    String[] numbersKilos;
    String[] numbersGramos;

    User user;
    Mascotas addMascota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_weight);

        user = (User)getIntent().getSerializableExtra("UserObject");
        addMascota = (Mascotas)getIntent().getSerializableExtra("MascotaObject");


        npKilos = findViewById(R.id.npKilos);
        npGramos = findViewById(R.id.npGramos);
        btnNext = findViewById(R.id.btn_next2);

       btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMascota.setPeso(calcularPeso(kilos,gramos));


                Intent addPetPhoto = new Intent(v.getContext(), AddPetPhotoActivity.class);
                addPetPhoto.putExtra("UserObject",user);
                addPetPhoto.putExtra("MascotaObject",addMascota);
                startActivity(addPetPhoto);

            }
        });


        List<String> numberKilosList = new ArrayList();

        for (int i = 60; i > 0; i--)
        {
            numberKilosList.add(String.valueOf(i));
        }

        numbersKilos = numberKilosList.toArray(new String[numberKilosList.size()]);

        npKilos.setDisplayedValues(null);

        npKilos.setMinValue(0);
        npKilos.setMaxValue(numbersKilos.length-1);
        npKilos.setDisplayedValues(numbersKilos);
        //El numero 30, se encuentra en numbersKilos[30]
        npKilos.setValue(30);
        kilos = 30;

        npKilos.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                kilos = newVal;
            }
        });


        List<String> numberGramosList = new ArrayList();

        for (int i = 9; i > -1; i--)
        {
            numberGramosList.add(String.valueOf(i));
        }

        numbersGramos = numberGramosList.toArray(new String[numberGramosList.size()]);

        npGramos.setDisplayedValues(null);

        npGramos.setMinValue(0);
        npGramos.setMaxValue(numbersGramos.length-1);
        npGramos.setDisplayedValues(numbersGramos);
        //El valor 0 se encuentra en la posicion 9, npGramos[9]
        npGramos.setValue(9);
        gramos = 9;

        npGramos.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                gramos = newVal;
            }
        });


    }

   private Double calcularPeso(int positionKilos, int positionGramos) {

        int kilos = Integer.parseInt(numbersKilos[positionKilos]);
        int gramos = Integer.parseInt(numbersGramos[positionGramos]);

        Double peso = kilos + ((double)gramos/(double)10);
        //Toast.makeText(getApplicationContext(),"Peso: "+peso, Toast.LENGTH_SHORT).show();
        return peso;
    }
}