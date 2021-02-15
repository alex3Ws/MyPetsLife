package com.argdev.mypetslife.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.argdev.mypetslife.Entities.Mascotas;
import com.argdev.mypetslife.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;



public class SelectAddPetAdapter extends RecyclerView.Adapter<SelectAddPetAdapter.ViewHolderPets> {


    List<Mascotas> mascotas;


    public SelectAddPetAdapter(List<Mascotas> mascotas) {
        this.mascotas = mascotas;

    }

    @NonNull
    @Override
    public ViewHolderPets onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mascotas_cardview,parent,false);

        return new ViewHolderPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPets holder, int position) {

        if(position != 2){
            Picasso.get().load(mascotas.get(position).getUrlFoto())
                    .resize(175,175).into(holder.mascota);
        }
        else{

            holder.cardViewMascotas.setVisibility(View.GONE);
            holder.cardViewMascotasAdd.setVisibility(View.VISIBLE);
        }



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
