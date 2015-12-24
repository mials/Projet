package com.example.hani.projet;

import android.os.AsyncTask;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Site;

/**
 * Created by Hani on 24/12/2015.
 */
public class ModifierSite extends AsyncTask<Site, Void , Void> {

    Site site;
    FormulaireModifierSite formulaireAjout;
    DAOMySqlSite daoMySqlSite;

    public ModifierSite(FormulaireModifierSite formulaireAjout, Site site) {
        this.formulaireAjout = formulaireAjout;
        this.site = site;
        this.daoMySqlSite = new DAOMySqlSite(this.formulaireAjout);
        this.daoMySqlSite.dialog.show();
    }

    @Override
    protected Void doInBackground(Site... params) {
        this.daoMySqlSite.updateSite(site);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.daoMySqlSite.dialog.dismiss();
        this.formulaireAjout.finish();
    }
}
