package com.mycomany.entities;

import java.util.Date;

public class Reclamation {

    private int id;
    private int userId;
    private int coachId;
    private int clubId;
    private int articleId;
    private Date date;
    private String etat;
    private String reponse;
    private String type;
    private String message;

    public Reclamation() {
    }

    public Reclamation(int id, int userId, int coachId, int clubId, int articleId, Date date, String etat, String reponse, String type, String message) {
        this.id = id;
        this.userId = userId;
        this.coachId = coachId;
        this.clubId = clubId;
        this.articleId = articleId;
        this.date = date;
        this.etat = etat;
        this.reponse = reponse;
        this.type = type;
        this.message = message;
    }

    public Reclamation(int userId, int coachId, int clubId, int articleId, Date date, String etat, String reponse, String type, String message) {
        this.userId = userId;
        this.coachId = coachId;
        this.clubId = clubId;
        this.articleId = articleId;
        this.date = date;
        this.etat = etat;
        this.reponse = reponse;
        this.type = type;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}