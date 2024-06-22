package com.alura.LiterAluraChallengeDGD.Repository;

import com.alura.LiterAluraChallengeDGD.Model.Libro;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query(value = "SELECT * FROM libros" ,nativeQuery = true)
    List<Libro> listaDeLibros();

    @Query("SELECT l FROM Libro l WHERE l.nombreAutor ILIKE %:nombreAutor%")
    List<Libro>busquedaPorAutor(String nombreAutor);

    @Query("SELECT l FROM Libro l WHERE l.anioDeNacimiento <= :anioBuscado AND l.anioDeFallecimiento >= :anioBuscado")
    List<Libro>busquedaPorAnio(int anioBuscado);

    @Query("SELECT l FROM Libro l")
    List<Libro>todaLaInfo();






}
