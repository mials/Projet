package com.example.hani.projet.DAO.COR;

import com.example.hani.projet.Model.Site;

import java.util.ArrayList;

/**
 * Created by Hani on 23/12/2015.
 */
public interface ChargerSelonCategorie {

    ArrayList<Site> charger(String categorie , float latitude , float longitude);

}
