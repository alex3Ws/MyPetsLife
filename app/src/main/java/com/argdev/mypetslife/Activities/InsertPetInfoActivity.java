package com.argdev.mypetslife.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertPetInfoActivity extends AppCompatActivity {

    EditText petBirthDay,petName;
    ImageButton btn_Next;
    User user;
    Mascotas addMascota;

    private int dia,mes,año;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pet_info);

        petBirthDay = findViewById(R.id.etPetBirthDate);
        petName = findViewById(R.id.etPetName);
        btn_Next = findViewById(R.id.btn_next);


        user = (User)getIntent().getSerializableExtra("UserObject");
        addMascota = (Mascotas)getIntent().getSerializableExtra("MascotaObject");


        petName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!petBirthDay.getText().toString().equals("") && petName.getText().toString().equals("")){

                    btn_Next.setVisibility(View.VISIBLE);

                }
                else{
                    btn_Next.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        petBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                año = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertPetInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar selectBirthday = Calendar.getInstance();
                        selectBirthday.set(year, month, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        String dateString = dateFormat.format(selectBirthday.getTime());

                        petBirthDay.setText(dateString);


                    }
                },año,mes,dia);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                datePickerDialog.show();

                if(!petName.getText().toString().equals("")){
                    btn_Next.setVisibility(View.VISIBLE);

                }
            }
        });


        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMascota.setNombreMascota(petName.getText().toString());
                addMascota.setFechaNacimiento(petBirthDay.getText().toString());
                
                Intent selectWeight = new Intent(v.getContext(), SelectWeightActivity.class);
                selectWeight.putExtra("UserObject",user);
                selectWeight.putExtra("MascotaObject",addMascota);
                startActivity(selectWeight);

            }
        });



    }
}