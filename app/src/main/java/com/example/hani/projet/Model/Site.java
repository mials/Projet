package com.example.hani.projet.Model;

import java.io.Serializable;

/**
 * Created by slaim1u on 03/12/2015.
 */
public class Site implements Serializable{

    int id;
    String nom , categorie, resume, adresse;
    float latitude;
    float longitude ;


    public Site(String nom , String categorie , float latitude , float longitude ,String adresse ,String resume ) {

        this.adresse = adresse;
        this.categorie = categorie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.resume = resume;
    }

    public Site(int id, String nom , String categorie , float latitude , float longitude ,String adresse ,String resume ) {

        this.id = id;
        this.adresse = adresse;
        this.categorie = categorie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.resume = resume;
    }


    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getResume() {
        return resume;
    }

    public String getAdresse() {
        return adresse;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getId() {
        return id;
    }
}
