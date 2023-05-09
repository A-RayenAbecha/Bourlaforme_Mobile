/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author aziz3
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String email;
    private String image;
    private String nom;
    private String prenom;
    private String password;

    public Utilisateur(String email, String image, String nom, String prenom, String password) {
        this.email = email;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String email) {
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", adresse=" + email + ", imageDeProfil=" + image + '}';
    }

    public Utilisateur(int id, String email, String image, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(String email, String image, String nom, String prenom) {
        this.email = email;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    
    
    
}