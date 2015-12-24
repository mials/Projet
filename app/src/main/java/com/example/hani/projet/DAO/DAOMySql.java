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
    abstract ArrayList<HashMap<String, String>> chargerTous();
    abstract ArrayList<Site> chargerSelonCategorie(String categorie , float latitude , float longitude);


}
