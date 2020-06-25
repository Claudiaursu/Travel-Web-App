package com.example.proiectjava.model;
import java.sql.Date;

public class Inregistrare {
    private int id_inregistrare;
    private int id_utilizator;
    private int id_vacanta;
    private Date data_i;

    public Inregistrare(){
    }

    public Inregistrare(int id_inregistrare, int id_utilizator, int id_vacanta, Date data_i) {
        this.id_inregistrare = id_inregistrare;
        this.id_utilizator = id_utilizator;
        this.id_vacanta = id_vacanta;
        this.data_i = data_i;
    }

    public int getId_inregistrare() {
        return id_inregistrare;
    }

    public int getId_utilizator() {
        return id_utilizator;
    }

    public int getId_vacanta() {
        return id_vacanta;
    }

    public Date getData_i() {
        return data_i;
    }

    public void setId_inregistrare(int id_inregistrare) {
        this.id_inregistrare = id_inregistrare;
    }

    public void setId_utilizator(int id_utilizator) {
        this.id_utilizator = id_utilizator;
    }

    public void setId_vacanta(int id_vacanta) {
        this.id_vacanta = id_vacanta;
    }

    public void setData_i(Date data_i) {
        this.data_i = data_i;
    }
}

