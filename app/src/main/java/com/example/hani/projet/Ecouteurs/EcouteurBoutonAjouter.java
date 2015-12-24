package com.example.hani.projet.Ecouteurs;

import android.app.Activity;
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
    private Activity activity;


    public EcouteurBoutonAjouter(Activity maps) {
        this.activity = maps;

    }


    @Override
    public void onClick(View v) {
        Intent monIntent = new Intent(this.activity,com.example.hani.projet.FormulaireAjout.class);
        this.activity.startActivity(monIntent);
        
    }
}
