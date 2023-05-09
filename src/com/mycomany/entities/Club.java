/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

/**
 *
 * @author obba
 */
public class Club {
    private int id;
    private String nom, localisation,telephone, description, type_activite,image;

    public Club(String nom, String localisation, String telephone, String description, String type_activite) {
        
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.type_activite = type_activite;
        this.image=image;
        this.telephone=telephone;
    }

    public Club(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Club{" + "nom=" + nom + ", localisation=" + localisation + ", telephone=" + telephone + ", description=" + description + ", type_activite=" + type_activite + ", image=" + image + '}';
    }

    public Club(String nom, String localisation, String telephone, String description, String type_activite, String image) {
        this.nom = nom;
        this.localisation = localisation;
        this.telephone = telephone;
        this.description = description;
        this.type_activite = type_activite;
        this.image = image;
    }

   

    public Club() {
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType_activite() {
        return type_activite;
    }

    public void setType_activite(String type_activite) {
        this.type_activite = type_activite;
    }


   
    
    
    
    
    
    
    
    
    
}