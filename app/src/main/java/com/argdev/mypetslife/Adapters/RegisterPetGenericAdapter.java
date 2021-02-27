package com.argdev.mypetslife.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.argdev.mypetslife.Activities.SelectBreedActivity;
import com.argdev.mypetslife.Activities.SelectPetSpeciesActivity;
import com.argdev.mypetslife.Entities.InfoMascotas;
import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RegisterPetGenericAdapter extends RecyclerView.Adapter<RegisterPetGenericAdapter.ViewHolderPets>{

    List<InfoMascotas> infoMascotasList;
    String identifier;  //Valores ESP, o BREED
    User user;

    public RegisterPetGenericAdapter(List<InfoMascotas> infoMascotasList, String identifier, User user) {

        this.infoMascotasList = infoMascotasList;
        this.identifier = identifier;
        this.user = user;

    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if(identifier.equals("ESP")){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_species_cardview,parent,false);

        }
        else {

             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_select_breed,parent,false);
        }

        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, int position) {

        if(identifier.equals("ESP")){

            Picasso.get().load(infoMascotasList.get(position).getSpeciesBreedUrl())
                    .resize(400,400).into(holder.speciesImage);

            holder.speciesName.setText(infoMascotasList.get(position).getSpeciesBreed());


            if(position == 0){

                holder.speciesImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mascotas addMascota = new Mascotas();
                        addMascota.setEspecie(infoMascotasList.get(position).getSpeciesBreed());

                        Intent selectBreed = new Intent(v.getContext(), SelectBreedActivity.class);
                        selectBreed.putExtra("UserObject",user);
                        selectBreed.putExtra("MascotaObject",addMascota);
                        v.getContext().startActivity(selectBreed);

                    }
                });
            }

        }
        else {

            Picasso.get().load(infoMascotasList.get(position).getSpeciesBreedUrl())
                    .resize(400,400).into(holder.breedImage);

            holder.breedName.setText(infoMascotasList.get(position).getSpeciesBreed());

        }




    }

    @Override
    public int getItemCount() { return infoMascotasList.size(); }

    public class ViewHolderPets extends RecyclerView.ViewHolder {


        ImageView speciesImage,breedImage;
        TextView speciesName,breedName;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);

            speciesImage= itemView.findViewById(R.id.ivSpecies);
            speciesName= itemView.findViewById(R.id.tvSpecies);

            breedImage = itemView.findViewById(R.id.iv_breed_image);
            breedName = itemView.findViewById(R.id.tv_breedName);


        }
    }


}
