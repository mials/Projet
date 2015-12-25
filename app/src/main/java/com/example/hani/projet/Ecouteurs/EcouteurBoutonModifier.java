package com.example.hani.projet.Ecouteurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hani.projet.AffichageSite;
import com.example.hani.projet.FormulaireModifierSite;
import com.example.hani.projet.Model.Outils;

/**
 * Created by slaim1u on 03/12/2015.
 */
public class EcouteurBoutonModifier implements Button.OnClickListener {

    private AffichageSite maps;

    public EcouteurBoutonModifier(AffichageSite maps) {
        this.maps = maps;
    }

    @Override
    public void onClick(View v) {
        Intent monIntent = new Intent(this.maps,FormulaireModifierSite.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(Outils.TAG_SITE, this.maps.getSiteAffiche());
        monIntent.putExtras(mBundle);
        this.maps.startActivity(monIntent);
    }
}
