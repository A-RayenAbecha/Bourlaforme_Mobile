/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author obba
 */
public class Participation {
    private int id;
    
    private int id_club;
    private String dateDebut;
    private String dateFin;

    public Participation() {
    }

    public Participation(int id) {
        this.id = id;
    }

    
    public Participation(int id, int id_club, String dateDebut, String dateFin) {
        this.id = id;
        this.id_club = id_club;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Participation(int id_club, String dateDebut, String dateFin) {
        this.id_club = id_club;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", id_club=" + id_club + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
}
