package com.alura.LiterAluraChallengeDGD.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;
@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @Transient
    @JsonIgnore
    private List<AutorDTO> autor;

    private List<String> idiomas;
    private Double numeroDescargas;
    private String nombreAutor;
    private int anioDeNacimiento;
    private int anioDeFallecimiento;

    public Libro(){
    }

    public Libro(String titulo, List<AutorDTO> autor, List<String> idiomas, Double nDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDescargas = nDescargas;
        if (!autor.isEmpty()) {
            AutorDTO autor2 = autor.get(0);
            this.nombreAutor = autor2.nombreAutor1();
            this.anioDeNacimiento = autor2.anioDeNacimiento1();
            this.anioDeFallecimiento = autor2.anioDeFallecimiento1();
        }
    }


    @Override
    public String toString() {
        return "\n-------Libro-------\n" +
                "Titulo: " + titulo + " ~~~  Autor: " + nombreAutor + " ~~~ Idiomas: " + idiomas + " ~~~ Numero de Descargas " + numeroDescargas;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorDTO> getAutor() {
        return autor;
    }

    public void setAutor(List<AutorDTO> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }


}




