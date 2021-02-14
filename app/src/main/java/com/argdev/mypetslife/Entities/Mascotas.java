package com.argdev.mypetslife.Entities;

import java.io.Serializable;
import java.util.List;

public class Mascotas  implements Serializable {
    private String Prueba;

    private String Prueba2;

    public Mascotas(){

    }

    public Mascotas(String prueba, String prueba2) {
        Prueba = prueba;
        Prueba2 = prueba2;
    }

    public String getPrueba() {
        return Prueba;
    }

    public void setPrueba(String prueba) {
        Prueba = prueba;
    }

    public String getPrueba2() {
        return Prueba2;
    }

    public void setPrueba2(String prueba2) {
        Prueba2 = prueba2;
    }
}
