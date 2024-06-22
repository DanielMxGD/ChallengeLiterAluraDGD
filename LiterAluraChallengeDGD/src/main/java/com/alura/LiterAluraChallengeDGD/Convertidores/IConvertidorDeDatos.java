package com.alura.LiterAluraChallengeDGD.Convertidores;

public interface IConvertidorDeDatos {
    <T> T deserializa(String json, Class<T> clase);
}
