package com.alura.LiterAluraChallengeDGD.Model;

import java.util.List;

public class Autor {
    String nombre;
    Double anioDeNacimiento;
    Double anioDeFallecimiento;

    public Autor(String nombreAutor, Double nacimiento, Double Fallecimiento) {
        this.nombre = nombreAutor;
        this.anioDeNacimiento =nacimiento;
        this.anioDeFallecimiento = Fallecimiento;
    }


    public String getNombre() {
        return nombre;
    }

    public Double getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public Double getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }
}
