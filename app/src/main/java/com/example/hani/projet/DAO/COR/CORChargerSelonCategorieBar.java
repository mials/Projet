package com.example.hani.projet.DAO.COR;

import com.example.hani.projet.Model.Site;

import java.util.ArrayList;

/**
 * Created by Hani on 23/12/2015.
 */
public class CORChargerSelonCategorieBar extends CORChargerSelonCategorie {

    public CORChargerSelonCategorieBar(CORChargerSelonCategorie expertSuivant) {
        super(expertSuivant);
    }

    @Override
    public ArrayList<Site> charger1(String categorie, float latitude, float longitude) {
        return null;
    }
}
