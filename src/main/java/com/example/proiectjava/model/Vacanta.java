package com.example.proiectjava.model;

public class Vacanta {
    private int id_vacanta;
    private String tara;
    private int durata;
    private int cost;
    private String autor;

    public Vacanta(){

    }

    public Vacanta(int id_vacanta, String tara, int durata, int cost) {
        this.id_vacanta = id_vacanta;
        this.tara = tara;
        this.durata = durata;
        this.cost = cost;
    }

    public int getId_vacanta() {
        return id_vacanta;
    }

    public String getTara() {
        return tara;
    }

    public int getDurata() {
        return durata;
    }

    public int getCost() {
        return cost;
    }

    public void setId_vacanta(int id_vacanta) {
        this.id_vacanta = id_vacanta;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}

