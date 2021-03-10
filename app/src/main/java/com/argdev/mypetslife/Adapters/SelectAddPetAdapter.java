package com.argdev.mypetslife.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.argdev.mypetslife.Activities.PetInfoActivity;
import com.argdev.mypetslife.Activities.SelectPetSpeciesActivity;
import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.Entities.User;
import com.argdev.mypetslife.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class SelectAddPetAdapter extends RecyclerView.Adapter<SelectAddPetAdapter.ViewHolderPets> {


    List<Mascotas> mascotas = new ArrayList<>();
    int mascotasSize;
    Boolean flagMascotas = false;
    User user;


    public SelectAddPetAdapter(List<Mascotas> mascotas, User user) {
        this.mascotas.clear();
        this.mascotas = mascotas;
        this.mascotasSize = mascotas.size();
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mascotas_cardview,parent,false);

        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, int position) {


        //Si la posicion actual, no es el objeto vacío añadido para mostrar el icono de añadir
        if(position <= mascotasSize && mascotasSize == mascotas.size()){

            // Si es la ultima posicion del arraylist, y no ha entrado antes (flagMascotas), y mientras el arraylist tenga menos de 3 objetos
            if (position == mascotas.size() - 1 && !flagMascotas && mascotasSize < 3){
                mascotas.add(new Mascotas());
                flagMascotas = true;
            }


            Picasso.get().load(mascotas.get(position).getUrlFoto())
                    .resize(175,175).into(holder.mascota);

            holder.mascota.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent petInfo = new Intent(v.getContext(), PetInfoActivity.class);
                    petInfo.putExtra("UserObject",user);
                    v.getContext().startActivity(petInfo);
                }
            });


        }
        else
        {
            //El maximo que se permite añadir son 3 Mascotas, asique si hay 2 o menos añadidas, aparecera el icono de añadir Mascota
            if(mascotasSize < 3){
                holder.cardViewMascotas.setVisibility(View.GONE);
                holder.cardViewMascotasAdd.setVisibility(View.VISIBLE);
            }

        }



        holder.addMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent createPet = new Intent(v.getContext(), SelectPetSpeciesActivity.class);
                createPet.putExtra("UserObject",user);
                v.getContext().startActivity(createPet);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class ViewHolderPets extends RecyclerView.ViewHolder {

        CircularImageView mascota;
        ImageView addMascota;
        CardView cardViewMascotas;
        CardView cardViewMascotasAdd;

        public ViewHolderPets(@NonNull View itemView) {
            super(itemView);

            mascota= itemView.findViewById(R.id.pet_image);
            addMascota= itemView.findViewById(R.id.pet_image_add);
            cardViewMascotas= itemView.findViewById(R.id.cd_mascotas);
            cardViewMascotasAdd= itemView.findViewById(R.id.cd_mascotas_add);

        }

    }
}
