package com.example.mypetslife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//TODO: editText cuando se le hacen click quitar el hint y volverlo a poner al desfocusearlo. Validaciones de campos al clickar botones

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    TextView tvForgotPassword;
    Button btn_login,btn_google,btn_facebook,btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);


        etPassword = findViewById(R.id.etPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSelectPet = new Intent(v.getContext(),SelectOrAddPetActivity.class);
                startActivity(intentAddSelectPet);

            }
        });

        btn_google = findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_facebook = findViewById(R.id.btn_facebook);
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_signIn = findViewById(R.id.btn_SignIn);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent(v.getContext(),SignInActivity.class);
                startActivity(intentSignIn);
            }
        });

    }




}