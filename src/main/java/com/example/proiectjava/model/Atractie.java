package com.example.proiectjava.model;

public class Atractie {
    private int id_atractie;
    private int id_vacanta;
    private String oras;
    private String tip;
    private String nume;
    private float pret;
    private String autor;
    private String poza;

    public Atractie() {};

    public Atractie(int id_atractie, int id_vacanta, String oras, String tip, String nume, float pret, String poza) {
        this.id_atractie = id_atractie;
        this.id_vacanta = id_vacanta;
        this.oras = oras;
        this.tip = tip;
        this.nume = nume;
        this.pret = pret;
        this.poza = poza;
    }

    public int getId_atractie() {
        return id_atractie;
    }

    public void setId_atractie(int id_atractie) {
        this.id_atractie = id_atractie;
    }

    public int getId_vacanta() {
        return id_vacanta;
    }

    public void setId_vacanta(int id_vacanta) {
        this.id_vacanta = id_vacanta;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getAutor() {
        return autor;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
