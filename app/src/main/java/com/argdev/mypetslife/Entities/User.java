package com.argdev.mypetslife.Entities;

public class User {

    private String IDUser;
    //Mascota: 1-Si tiene mascota 0-Si no
    private Boolean Mascota;
    //Mascotas - Informacion de cada una de las mascotas
    private Mascotas Mascotas;

    public User(String IDUser, Boolean Mascota, Mascotas Mascotas) {
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

    public void setMascota(Boolean Mascota) {
        this.Mascota = Mascota;
    }


    public Mascotas getMascotas() {
        return Mascotas;
    }

    public void setMascotas(Mascotas Mascotas) {
        this.Mascotas = Mascotas;
    }
}
