/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Administrateur
 */
public class Article {
    
    private int id,prix;
    private String nom, description,image,etat;

    public Article() {
    }

    public Article(int id) {
        this.id = id;
    }

    public Article(int id, int prix, String nom, String description) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
    }
    
    

    public Article(int id, int prix, String nom, String description, String image, String etat) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.etat = etat;
    }

    public Article(int prix, String nom, String description, String image, String etat) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.etat = etat;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setQuantity(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
