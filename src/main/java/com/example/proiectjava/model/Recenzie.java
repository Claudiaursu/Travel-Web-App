package com.example.proiectjava.model;

public class Recenzie {

    private int id_recenzie;
    private int id_atractie;
    private String titlu;
    private float nota;
    private String parere;
    private String autor;

    public Recenzie() {};

    public Recenzie(int id_recenzie, int id_atractie, String titlu, float nota, String parere) {
        this.id_recenzie = id_recenzie;
        this.id_atractie = id_atractie;
        this.titlu = titlu;
        this.nota = nota;
        this.parere = parere;
    }

    public int getId_recenzie() {
        return id_recenzie;
    }

    public void setId_recenzie(int id_recenzie) {
        this.id_recenzie = id_recenzie;
    }

    public int getId_atractie() {
        return id_atractie;
    }

    public void setId_atractie(int id_atractie) {
        this.id_atractie = id_atractie;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getParere() {
        return parere;
    }

    public void setParere(String parere) {
        this.parere = parere;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
