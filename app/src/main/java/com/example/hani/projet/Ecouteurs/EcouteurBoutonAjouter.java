package com.example.hani.projet.Ecouteurs;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.MapsActivity;
import com.example.hani.projet.Model.Site;

/**
 * Created by Hani on 13/12/2015.
 */
public class EcouteurBoutonAjouter implements Button.OnClickListener{
    private MapsActivity maps;


    public EcouteurBoutonAjouter(MapsActivity maps) {
        this.maps = maps;

    }


    @Override
    public void onClick(View v) {
        Intent monIntent = new Intent(this.maps,com.example.hani.projet.FormulaireAjout.class);
        this.maps.startActivity(monIntent);
        
    }
}
