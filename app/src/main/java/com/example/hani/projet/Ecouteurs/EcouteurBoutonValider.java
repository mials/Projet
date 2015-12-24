package com.example.hani.projet.Ecouteurs;

import android.view.View;
import android.widget.Button;

import com.example.hani.projet.FormulaireModifierSite;
import com.example.hani.projet.Model.Site;
import com.example.hani.projet.ModifierSite;

/**
 * Created by Hani on 24/12/2015.
 */
public class EcouteurBoutonValider implements Button.OnClickListener  {

    FormulaireModifierSite modifierSite;

    public EcouteurBoutonValider(FormulaireModifierSite modifierSite) {
        this.modifierSite = modifierSite;
    }

    @Override
    public void onClick(View v) {
        Site site = new Site(
                this.modifierSite.site.getId(),
                this.modifierSite.getNom().toString(),
                this.modifierSite.getSpinnercategorier().getSelectedItem().toString(),
                Float.parseFloat(this.modifierSite.getLatitude().toString()),
                Float.parseFloat(this.modifierSite.getLongitude().toString()),
                this.modifierSite.getAdresse().toString(),
                this.modifierSite.getResume().toString());

        new ModifierSite(this.modifierSite, site).execute();
    }
}
