package com.argdev.mypetslife.Entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Mascotas  implements Serializable {

    private String NombreMascota;
    private String Especie;
    private String Raza;
    private String Subraza1;
    private String Subraza2;
    private String FechaNacimiento;
    private String UrlFoto;
    private Double Peso;

    public Mascotas(){

    }

    public Mascotas(String nombreMascota, String especie, String raza, String subraza1, String subraza2, String fechaNacimiento, String urlFoto) {
        NombreMascota = nombreMascota;
        Especie = especie;
        Raza = raza;
        Subraza1 = subraza1;
        Subraza2 = subraza2;
        FechaNacimiento = fechaNacimiento;
        UrlFoto = urlFoto;
    }

    public String getNombreMascota() {
        return NombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        NombreMascota = nombreMascota;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getSubraza1() {
        return Subraza1;
    }

    public void setSubraza1(String subraza1) {
        Subraza1 = subraza1;
    }

    public String getSubraza2() {
        return Subraza2;
    }

    public void setSubraza2(String subraza2) {
        Subraza2 = subraza2;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getUrlFoto() {
        return UrlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        UrlFoto = urlFoto;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }


    @Override
    public String toString() {
        return "Mascotas{" +
                "NombreMascota='" + NombreMascota + '\'' +
                ", Especie='" + Especie + '\'' +
                ", Raza='" + Raza + '\'' +
                ", Subraza1='" + Subraza1 + '\'' +
                ", Subraza2='" + Subraza2 + '\'' +
                ", FechaNacimiento='" + FechaNacimiento + '\'' +
                ", UrlFoto='" + UrlFoto + '\'' +
                ", Peso=" + Peso +
                '}';
    }
}
