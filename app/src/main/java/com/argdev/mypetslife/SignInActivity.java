package com.argdev.mypetslife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//TODO: editText cuando se le hacen click quitar el hint y volverlo a poner al desfocusearlo. Validaciones de campos al clickar botones

public class SignInActivity extends AppCompatActivity {

    EditText etEmail,etPassword,etRepeatPassword;
    Button btn_SignIn,btn_haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btn_SignIn = findViewById(R.id.btn_SignIn);

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSelectPet = new Intent(v.getContext(),SelectOrAddPetActivity.class);
                startActivity(intentAddSelectPet);
            }
        });

        btn_haveAccount = findViewById(R.id.btn_haveAccount);
        btn_haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intentLogin);
            }
        });

    }
}