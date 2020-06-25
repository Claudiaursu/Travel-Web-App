package com.example.proiectjava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilizator {
    private int id_utilizator;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String parola;
    private int activ;
    private String roles = "";

    public Utilizator(){

    }

    public Utilizator(int id_utilizator, String nume, String prenume, String email, String telefon, String roles) {
        this.id_utilizator = id_utilizator;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.activ = 1;
        this.roles = roles;
    }

    public int getId_utilizator() {
        return id_utilizator;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setId_utilizator(int id_utilizator) {
        this.id_utilizator = id_utilizator;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getActiv() {
        return activ;
    }

    public void setActiv(int activ) {
        this.activ = activ;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getRoleList(){
        if(roles.length() > 0){
            return Arrays.asList(roles.split(","));
        }
        else return new ArrayList<>();
    }
}
