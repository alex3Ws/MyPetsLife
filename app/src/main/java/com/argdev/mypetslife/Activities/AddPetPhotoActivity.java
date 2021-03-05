package com.argdev.mypetslife.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPetPhotoActivity extends AppCompatActivity {

    ImageButton btn_nextFoto;
    Button btn_OmitirFoto;
    ImageView pet_image_add;
    CircularImageView pet_image;

    StorageReference storage;
    private FirebaseFirestore db;

    Uri imageUriResultCrop;

    private final int CODE_IMG_GALLERY = 1;
    private  String IMAGE_NAME;


    User user;
    Mascotas addMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_photo);

        user = (User)getIntent().getSerializableExtra("UserObject");
        addMascota = (Mascotas)getIntent().getSerializableExtra("MascotaObject");

        //Recorrer Array Mascotas del user, y comprobar si alguna tiene el mismo nombre que la que se está creando, en cuyo caso,
        //comprobar cuantas, y añadir i+1 al numero final del nombre , 34543643556_Sparky_2;
        IMAGE_NAME = user.getIDUser() +"_"+addMascota.getNombreMascota() +"_"+ "2" + ".jpg";


        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        pet_image_add = findViewById(R.id.pet_image_add2);
        btn_nextFoto = findViewById(R.id.btn_nextFoto);
        btn_OmitirFoto = findViewById(R.id.btn_OmitirFoto);
        pet_image = findViewById(R.id.pet_image_2);

        Intent intentChooseImage = new Intent();
        intentChooseImage.setType("image/*");
        intentChooseImage.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,false);
        intentChooseImage.setAction(Intent.ACTION_PICK);

        pet_image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CropImage

                /*startActivityForResult(new Intent()
                        .setAction(Intent.ACTION_GET_CONTENT)
                .setType("IMAGE/JPG"), CODE_IMG_GALLERY);*/



                selectImage(intentChooseImage);



            }
        });

        pet_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage(intentChooseImage);
            }
        });


        btn_OmitirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadMascota(v);


            }
        });


        btn_nextFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent a la proxima activity sustituyendo urlfoto por la que seleccionó el usuario y registrando la nueva mascota en la bbdd

                StorageReference filePath = storage.child("Imagenes_Mascotas_Usuarios").child(imageUriResultCrop.getLastPathSegment());

                filePath.putFile(imageUriResultCrop).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                addMascota.setUrlFoto(imageUrl);

                                uploadMascota(v);
                            }
                        });


                    }
                });

            }
        });



    }

    private void selectImage(Intent intent) {

        startActivityForResult(Intent.createChooser(intent,"SELECCIONA LA APLICACION"),CODE_IMG_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_IMG_GALLERY && resultCode == RESULT_OK)
        {
            //Desde gallery
            Uri imageUri = data.getData();
            if(imageUri!=null){
                startCrop(imageUri);
            }

        }
        else if(requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK){

            imageUriResultCrop = null;
            imageUriResultCrop = UCrop.getOutput(data);

            if(imageUriResultCrop!=null){
                btn_OmitirFoto.setVisibility(View.GONE);
                btn_nextFoto.setVisibility(View.VISIBLE);

                pet_image_add.setVisibility(View.GONE);
                pet_image.setVisibility(View.VISIBLE);

                pet_image.setImageURI(null);
                pet_image.setImageURI(imageUriResultCrop);
            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Puta Mierda", Toast.LENGTH_SHORT).show();
        }

    }

    private void startCrop(@NonNull Uri uri){


        UCrop ucrop = UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),IMAGE_NAME)));

        ucrop.withAspectRatio(1,1);
        //ucrop.withAspectRatio(3,4);
        //ucrop.withAspectRatio(2,3);
        //ucrop.withAspectRatio(16,9);
        //ucrop.useSourceImageAspectRatio();

        ucrop.withMaxResultSize(450,450);
        ucrop.withOptions(getCropOptions());
        ucrop.start(AddPetPhotoActivity.this);


    }

    private UCrop.Options getCropOptions(){

        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(70);

        //CompressType
        //options.setCompressionFormat(Bitmap.CompressFormat.PNG);
        //options.setCompressionFormat(Bitmap.CompressFormat.JPEG);

        //UI
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(false);

        //Colors
        options.setStatusBarColor(getResources().getColor(R.color.design_default_color_primary_dark));
        options.setToolbarColor(getResources().getColor(R.color.design_default_color_primary_dark));

        options.setToolbarTitle("Recortar imagen");

        return options;

    }

    private void MakeIntent(View view){

        Intent selectPet = new Intent(view.getContext(), SelectOrAddPetActivity.class);

        if(user.getMascotas().size() != 0){
            user.removeMascota(user.getSize() - 1);
        }

        user.addMascotas(addMascota);

        selectPet.putExtra("UserObject",user);
        view.getContext().startActivity(selectPet);

    }


    private void uploadMascota(View v){

        Map<String,Object> map = new HashMap<>();
        map.put("Especie",addMascota.getEspecie());
        map.put("Raza",addMascota.getRaza());
        map.put("Subraza1",addMascota.getSubraza1());
        map.put("Subraza2",addMascota.getSubraza2());
        map.put("FechaNacimiento",addMascota.getFechaNacimiento());
        map.put("UrlFoto",addMascota.getUrlFoto());
        map.put("Peso",addMascota.getPeso());


        db.collection("Users").document(user.getEmail()).collection("Mascotas")
                .document(addMascota.getNombreMascota()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task2) {

                if(task2.isSuccessful()){


                    if(user.getMascotas().size() < 1){

                        Map<String,Object> map2 = new HashMap<>();
                        map2.put("Mascota",true);

                        db.collection("Users").document(user.getEmail()).update(map2);

                        MakeIntent(v);
                    }
                    else{
                        MakeIntent(v);
                    }


                }


            }
        });




    }

}