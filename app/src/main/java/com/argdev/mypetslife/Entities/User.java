package com.argdev.mypetslife.Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String email;
    private String IDUser;
    //Mascota: 1-Si tiene mascota 0-Si no
    private Boolean Mascota;
    //Mascotas - Informacion de cada una de las mascotas
    private List<Mascotas> Mascotas = new ArrayList<>();

    public User() {

    }

    public User(String email,String IDUser, Boolean Mascota, List<Mascotas> Mascotas) {
        this.email = email;
        this.IDUser = IDUser;
        this.Mascota = Mascota;
        this.Mascotas = Mascotas;
    }

    public String getIDUser() {

        return IDUser;
    }

    public void setIDUser(String IDUser) {

        this.IDUser = IDUser;
    }

    public Boolean getMascota() {

        return Mascota;
    }

    public void setMascota(Boolean Mascota){

        this.Mascota = Mascota;
    }


    public List<Mascotas>  getMascotas() {

        return Mascotas;
    }

    public void setMascotas(List<Mascotas> Mascotas) {

        this.Mascotas = Mascotas;
    }

    public Integer  getSize(){

        return Mascotas.size();

    }

    public void  removeMascota(int position){

        this.Mascotas.remove(position);

    }

    public void  addMascotas(Mascotas mascota){

        this.Mascotas.add(mascota);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toStringF(){
        String prueba = "";
        for(int i = 0; i<Mascotas.size() ;i++){

            prueba = prueba +Mascotas.get(i).getNombreMascota()+", "+Mascotas.get(i).getEspecie()+", "+Mascotas.get(i).getRaza()+" //"
                    +", "+Mascotas.get(i).getSubraza1()+", "+Mascotas.get(i).getSubraza2()
                    +", "+Mascotas.get(i).getFechaNacimiento()+", "+Mascotas.get(i).getUrlFoto();

        }

        prueba = IDUser+" "+Mascota+" // "+ prueba;

        return prueba;
    }

}
