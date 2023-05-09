/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import com.mycomany.entities.Article;
import java.util.Date;
/**
 *
 * @author Administrateur
 */
public class Commentaire {
    
    private int id;
    private int article;
    private String auteur,contenu;
    private Date date;
    private String date2;

    public Commentaire() {
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    
    public Commentaire(int article, String auteur, String contenu) {
        this.article = article;
        this.auteur = auteur;
        this.contenu = contenu;
    }
    
    

    public Commentaire(int id, int article, String auteur, String contenu, Date date) {
        this.id = id;
        this.article = article;
        this.auteur = auteur;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(int id, int article, String auteur, String contenu) {
        this.id = id;
        this.article = article;
        this.auteur = auteur;
        this.contenu = contenu;
    }

    

   
    public Commentaire(int id) {
        this.id = id;
    }

    public Commentaire(String text, int article_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    

    

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
