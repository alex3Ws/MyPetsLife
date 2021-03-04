package com.argdev.mypetslife.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//TODO: editText cuando se le hacen click quitar el hint y volverlo a poner al desfocusearlo. Validaciones de campos al clickar botones

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    TextView tvForgotPassword;
    Button btn_login,btn_google,btn_facebook,btn_signIn;

    private String email,password;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DocumentSnapshot document;
    public User user;
    public Mascotas mascota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);


        etPassword = findViewById(R.id.etPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btn_login = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = new User();

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

                                getUserData(v);

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

    private void getUserData(View view){

        db.collection("Users").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();
                    user.setEmail(document.getId());
                    user.setIDUser(document.getString("IDUser"));
                    user.setMascota(document.getBoolean("Mascota"));

                    if(user.getMascota().equals(true)){
                        getMascotasData(view);
                    }
                    else{
                        intentOnSucces(view);
                    }

                }
            }
        });
    }


    private void getMascotasData(View view){

        db.collection("Users").document(email).collection("Mascotas")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    List<Mascotas> listaMascotas = new ArrayList<Mascotas>();

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        mascota = document.toObject(Mascotas.class);
                        mascota.setNombreMascota(document.getId());
                        listaMascotas.add(mascota);
                    }

                    user.setMascotas(listaMascotas);

                    intentOnSucces(view);
                }

            }
        });

    }


    private void intentOnSucces(View view){
        Intent intentAddSelectPet = new Intent(view.getContext(), SelectOrAddPetActivity.class);
        intentAddSelectPet.putExtra("UserObject",user);
        startActivity(intentAddSelectPet);
    }




}