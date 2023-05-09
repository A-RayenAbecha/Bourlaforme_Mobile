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
public class BillingAddress {

    private int id;

    private String nom;

    private String Email;

    private String Address;

    private int Phone;

    private String Description;

    public BillingAddress() {
    }

    public BillingAddress(String nom, String Email, String Address, int Phone, String Description) {
        this.nom = nom;
        this.Email = Email;
        this.Address = Address;
        this.Phone = Phone;
        this.Description = Description;
    }

    public BillingAddress(int id, String nom, String Email, String Address, int Phone, String Description) {
        this.id = id;
        this.nom = nom;
        this.Email = Email;
        this.Address = Address;
        this.Phone = Phone;
        this.Description = Description;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "BillingAddress{" + "id=" + id + ", nom=" + nom + ", Email=" + Email + ", Address=" + Address + ", Phone=" + Phone + ", Description=" + Description + '}';
    }

}
