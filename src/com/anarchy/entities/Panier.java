/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anarchy.entities;

/**
 *
 * @author rayen
 */
public class Panier {

    private int id;
    private int Prix;
    private int Quantity;
    private String confirme;

    public Panier() {
    }

    public Panier(int Prix, int Quantity, String confirme) {
        this.Prix = Prix;
        this.Quantity = Quantity;
        this.confirme = confirme;
    }

    public Panier(int id, int Prix, int Quantity, String confirme) {
        this.id = id;
        this.Prix = Prix;
        this.Quantity = Quantity;
        this.confirme = confirme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getConfirme() {
        return confirme;
    }

    public void setConfirme(String confirme) {
        this.confirme = confirme;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", Prix=" + Prix + ", Quantity=" + Quantity + ", confirme=" + confirme + '}';
    }

}
