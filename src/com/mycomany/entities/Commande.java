/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author rayen
 */
public class Commande {

    int id;
    private int montant;
    private String date;
    private String ConfirmeAdmin;

    public Commande() {
    }

    public Commande(int id, int montant, String date, String ConfirmeAdmin) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.ConfirmeAdmin = ConfirmeAdmin;
    }

    public Commande(int montant, String date, String ConfirmeAdmin) {
        this.montant = montant;
        this.date = date;
        this.ConfirmeAdmin = ConfirmeAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String isConfirmeAdmin() {
        return ConfirmeAdmin;
    }

    public void setConfirmeAdmin(String ConfirmeAdmin) {
        this.ConfirmeAdmin = ConfirmeAdmin;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", montant=" + montant + ", date=" + date + ", ConfirmeAdmin=" + ConfirmeAdmin + '}';
    }

}
