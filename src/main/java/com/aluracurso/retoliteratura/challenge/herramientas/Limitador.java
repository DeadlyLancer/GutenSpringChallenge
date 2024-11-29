package com.aluracurso.retoliteratura.challenge.herramientas;


public class Limitador {

    public static String limite(String cadena, int limiteMax) {
        if (cadena.length() <= limiteMax) {
            return cadena;
        } else {
            return cadena.substring(0, limiteMax);
        }
    }
}