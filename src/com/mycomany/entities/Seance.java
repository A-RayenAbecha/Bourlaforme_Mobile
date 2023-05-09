/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
public class Seance {
    private int id;
     private int nbr_seance;
    private String description ;
        private int nbr_grp;
        private String color;
        private String titre;

        
    public Seance(int id, int nbr_seance, String description, int nbr_grp, String color, String titre) {
        this.id = id;
        this.nbr_seance = nbr_seance;
        this.description = description;
        this.nbr_grp = nbr_grp;
        this.color = color;
        this.titre = titre;
    }

    public Seance(int seance_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getColor() {
        return color;
    }

    public String getTitre() {
        return titre;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

   


    public Seance() {
     //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public int getNbr_grp() {
        return nbr_grp;
    }

    public String getDescription() {
        return description;
    }

    public int getNbr_seance() {
        return nbr_seance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbr_grp(int nbr_grp) {
        this.nbr_grp = nbr_grp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbr_seance(int nbr_seance) {
        this.nbr_seance = nbr_seance;
    }
   
}