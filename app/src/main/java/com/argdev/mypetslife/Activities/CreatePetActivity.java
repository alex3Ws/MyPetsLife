package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.argdev.mypetslife.R;

public class CreatePetActivity extends AppCompatActivity {

    //Spinner spSpecies;

    AutoCompleteTextView autoCompleteTextViewPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        autoCompleteTextViewPet = findViewById(R.id.autoCompleteTextViewPet);

        ArrayAdapter<String> spSpeciesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.species));

        autoCompleteTextViewPet.setText(spSpeciesAdapter.getItem(0).toString(),false);
        autoCompleteTextViewPet.setAdapter(spSpeciesAdapter);


        /*spSpecies = findViewById(R.id.spSpecies);


        ArrayAdapter<String> spSpeciesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.species));
        spSpeciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpecies.setAdapter(spSpeciesAdapter);*/


    }
}