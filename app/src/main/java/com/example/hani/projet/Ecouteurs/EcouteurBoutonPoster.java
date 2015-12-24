package com.example.hani.projet.Ecouteurs;

import android.view.View;
import android.widget.Button;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.FormulaireAjout;
import com.example.hani.projet.Model.Site;
import com.example.hani.projet.SauverSite;

/**
 * Created by slaim1u on 16/12/2015.
 */
public class EcouteurBoutonPoster  implements Button.OnClickListener {

    FormulaireAjout poster;

    public EcouteurBoutonPoster(FormulaireAjout poster) {
        this.poster = poster;

    }


    @Override
    public void onClick(View v) {
        Site site = new Site( this.poster.getNom().getText().toString(),
                              this.poster.getSpinnercategorier().getSelectedItem().toString(),
                              Float.parseFloat(this.poster.getLatitude().getText().toString()),
                              Float.parseFloat(this.poster.getLongitude().getText().toString()),
                              this.poster.getAdresse().getText().toString(),
                              this.poster.getResume().getText().toString());
        new SauverSite(site, this.poster).execute();

    }
}
