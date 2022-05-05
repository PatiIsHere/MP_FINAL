package com.mpfinal.mp_final.Model;

import java.time.LocalDate;

public abstract class Pracownik extends Osoba {
    private String pesel;
    //todo dodac do dokumentacji atrybut staz
    private int staz;

    public LocalDate getDataUrodzenia(){
        //po 2000roku do miesiaca dodaje sie liczbe 20
        //by wykluczyc powtarzalnosc miedzy np. 1901 i 2001
        //np 12.11.2019 -> 193112 jako pierwsze 6 cyfr
        return null;
    }

}
