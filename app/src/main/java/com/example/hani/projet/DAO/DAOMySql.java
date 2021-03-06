package com.example.hani.projet.DAO;

import com.example.hani.projet.Model.Site;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hani on 13/12/2015.
 */
public interface DAOMySql {

    abstract void sauver (Site site);
    abstract Site charger(int i);
    abstract String chargerTous();
    abstract ArrayList<Site> chargerSelonCategorie(String categorie, String latitudeMin ,String latitudeMax, String longitudeMin , String longitudeMax);
    abstract void updateSite(Site site);
    abstract void supprimerSite(int i);


}
