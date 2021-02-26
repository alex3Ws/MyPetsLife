package com.argdev.mypetslife.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.argdev.mypetslife.Entities.InfoMascotas;
import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.R;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RegisterPetGenericAdapter extends RecyclerView.Adapter<RegisterPetGenericAdapter.ViewHolderPets>{

    List<InfoMascotas> speciesList;
    String identifier;  //Valores ESP, o BREED

    public RegisterPetGenericAdapter(List<InfoMascotas> speciesList, String identifier) {

        this.speciesList = speciesList;
        this.identifier = identifier;

    }


    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_species_cardview,parent,false);

        return new ViewHolderPets(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, int position) {

            Picasso.get().load(speciesList.get(position).getSpeciesUrl())
                    .resize(400,400).into(holder.speciesImage);

            holder.speciesName.setText(speciesList.get(position).getSpecies());


            if(position == 0){

                holder.speciesImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mascotas addMascota = new Mascotas();
                        addMascota.setEspecie(speciesList.get(position).getSpecies());

                    }
                });
            }

    }

    @Override
    public int getItemCount() {
        return speciesList.size();
    }

    public class ViewHolderPets extends RecyclerView.ViewHolder {

        ImageView speciesImage;
        TextView speciesName;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);

            speciesImage= itemView.findViewById(R.id.ivSpecies);
            speciesName= itemView.findViewById(R.id.tvSpecies);


        }
    }


}
