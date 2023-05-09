/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Reservation {
  private int id;
  private Date date;
  private int seance;

    public Reservation(int id, Date date, int seance) {
        this.id = id;
        this.date = date;
        this.seance = seance;
    }

    public Reservation() {
    }

    public Reservation(int seance) {
        this.seance = seance;
    }

  
   

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getSeance() {
        return seance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeance(int seance) {
        this.seance = seance;
    }
  
}
