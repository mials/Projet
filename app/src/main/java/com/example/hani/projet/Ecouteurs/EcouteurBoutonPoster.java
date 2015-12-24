package com.example.hani.projet.Ecouteurs;

import android.view.View;
import android.widget.Button;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.FormulaireAjout;
import com.example.hani.projet.Model.Site;

/**
 * Created by slaim1u on 16/12/2015.
 */
public class EcouteurBoutonPoster  implements Button.OnClickListener {

    FormulaireAjout poster;
    private DAOMySqlSite daoMySqlSite;

    public EcouteurBoutonPoster(FormulaireAjout poster) {
        this.poster = poster;
        this.daoMySqlSite = new DAOMySqlSite(this.poster);
    }


    @Override
    public void onClick(View v) {
        Site site = new Site( this.poster.getNom().getText().toString(),
                              this.poster.getSpinnercategorier().getSelectedItem().toString(),
                              Float.parseFloat(this.poster.getLatitude().getText().toString()),
                              Float.parseFloat(this.poster.getLongitude().getText().toString()),
                              this.poster.getAdresse().getText().toString(),
                              this.poster.getResume().getText().toString());
        this.daoMySqlSite.sauver(site);
        this.poster.finish();

    }
}
