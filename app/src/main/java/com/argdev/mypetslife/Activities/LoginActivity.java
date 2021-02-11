package com.argdev.mypetslife.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.argdev.mypetslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

//TODO: editText cuando se le hacen click quitar el hint y volverlo a poner al desfocusearlo. Validaciones de campos al clickar botones

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    TextView tvForgotPassword;
    Button btn_login,btn_google,btn_facebook,btn_signIn;

    private String email,password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);


        etPassword = findViewById(R.id.etPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btn_login = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();


                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser AuthUser = mAuth.getCurrentUser();

                                //TODO: RECUPERAR INFO DE USUARIO DE LA BBDD

                                Intent intentAddSelectPet = new Intent(v.getContext(), SelectOrAddPetActivity.class);
                                startActivity(intentAddSelectPet);

                            } else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                                String errorMessage;
                                switch (errorCode) {
                                    case "ERROR_INVALID_EMAIL":
                                        errorMessage = "El email introducido no es un email válido";
                                        break;
                                    case "ERROR_WRONG_PASSWORD":
                                        errorMessage = "Contraseña errónea";
                                        break;
                                    case "ERROR_USER_NOT_FOUND":
                                        errorMessage = "No existe ningún usuario registrado con este email";
                                        break;
                                    case "ERROR_USER_DISABLED":
                                        errorMessage = "El usuario con este email ha sido deshabilitado";
                                        break;
                                    case "ERROR_TOO_MANY_REQUESTS":
                                        errorMessage = "Demasiados intentos, vuelva a intentarlo mas tarde";
                                        break;
                                    default:
                                        errorMessage = "Se ha producido un error, por favor intentelo mas tarde";

                                }

                                Toast.makeText(getApplicationContext(),errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



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
                Intent intentSignIn = new Intent(v.getContext(), SignUpActivity.class);
                startActivity(intentSignIn);
            }
        });

    }




}