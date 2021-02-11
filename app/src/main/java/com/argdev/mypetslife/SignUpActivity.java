package com.argdev.mypetslife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//TODO: editText cuando se le hacen click quitar el hint y volverlo a poner al desfocusearlo.

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail,etPassword,etRepeatPassword;
    Button btn_SignUp,btn_haveAccount;

    private String email,password,repeatpassword;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmailSignUp);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btn_SignUp = findViewById(R.id.btn_SignUp);



        //Al pulsar el boton registrarse
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                repeatpassword = etRepeatPassword.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference();

                //VALIDAR DE CAMPOS
                if(email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();

                }
                else if (!validarEmail(email)){

                    Toast.makeText(getApplicationContext(),"El email introducido no tiene un formato valido", Toast.LENGTH_SHORT).show();

                }
                else if(password.length() < 6 || password.length() > 16){

                    Toast.makeText(getApplicationContext(),"La contraseña debe tener entre 6 y 16 caracteres", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(repeatpassword)){

                    Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                //TODO: Validar contraseña que sea solo alfanuméricos.
                else{
                    registarUsuario(v);

                }


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

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void registarUsuario(View v) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("User",email);
                    map.put("Mascota",0);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {

                            if(task2.isSuccessful()){
                                Intent intentAddSelectPet = new Intent(v.getContext(),SelectOrAddPetActivity.class);
                                startActivity(intentAddSelectPet);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Se ha producido un error. Por favor, vuelva a intentarlo en unos minutos", Toast.LENGTH_SHORT).show();
                                //TODO: loguearse con el usuario creado y borrarlo. En firebase solo puedes borrar el usuario logueado
                            }

                        }
                    });

                }
                else{

                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                    switch(errorCode) {
                        case "ERROR_INVALID_EMAIL":
                            Toast.makeText(getApplicationContext(),"El email introducido no tiene un formato válido", Toast.LENGTH_SHORT).show();
                            break;
                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            Toast.makeText(getApplicationContext(),"El email introducido ya esta en uso", Toast.LENGTH_SHORT).show();
                            break;
                        case "ERROR_USER_DISABLED":
                            Toast.makeText(getApplicationContext(),"El email introducido ha sido baneado de la aplicacion por el administrador", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(),"Se ha producido un error, por favor, vuelva a intentarlo en unos minutos", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    //hacer switch case para los posibles errores, y asi dar una respuesta al usuario y meter todos los toast en strings para traducción a otros idiomas

                }
            }
        });

    }
}