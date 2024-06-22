package com.alura.LiterAluraChallengeDGD.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("name")
        String nombreAutor1,
        @JsonAlias("birth_year")
        int anioDeNacimiento1,
        @JsonAlias("death_year")
        int anioDeFallecimiento1)
{
        @Override
        public String nombreAutor1() {
                return nombreAutor1;
        }

        @Override
        public int anioDeNacimiento1() {
                return anioDeNacimiento1;
        }

        @Override
        public int anioDeFallecimiento1() {
                return anioDeFallecimiento1;
        }

        @Override
        public String toString() {
                return "AutorDTO{" +
                        "nombreAutor='" + nombreAutor1 + '\'' +
                        ", anioDeNacimiento=" + anioDeNacimiento1 +
                        ", anioDeFallecimiento=" + anioDeFallecimiento1 +
                        '}';
        }
}
