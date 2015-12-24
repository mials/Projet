package com.example.hani.projet.DAO.COR;

import com.example.hani.projet.Model.Site;

import java.util.ArrayList;

/**
 * Created by Hani on 23/12/2015.
 */
public abstract class CORChargerSelonCategorie implements ChargerSelonCategorie{

    private CORChargerSelonCategorie suivant;  // expert suivant dans la chaîne

    public CORChargerSelonCategorie(CORChargerSelonCategorie expertSuivant)
    {
        this.suivant = expertSuivant ;
    }

    public ArrayList<Site> charger(String categorie , float latitude , float longitude)
    {
        ArrayList<Site> s = this.charger(categorie,latitude,longitude);        // cet expert tente de résoudre le problème

        if  (s != null) 			// cet expert a trouvé une solution
            return s;
        else            			// échec de l’expert
            if  (this.suivant != null)  		// le problème est transmis à l’expert suivant
                return this.suivant.charger(categorie,latitude,longitude);
            else    // cet expert est le dernier de la chaîne
                return null; // donc échec de la chaîne
    }

    public abstract ArrayList<Site> charger1(String categorie , float latitude , float longitude);
}



