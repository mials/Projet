package com.example.hani.projet;

import android.os.AsyncTask;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

/**
 * Created by Hani on 24/12/2015.
 */
public class SauverSite extends AsyncTask<Site, Void , Void> {

    public Site site;
    public DAOMySqlSite daoMySqlSite;
    FormulaireAjout formulaireAjout;

    public SauverSite(Site site , FormulaireAjout formulaireAjout) {
        this.site = site;
        this.formulaireAjout =  formulaireAjout;
        this.daoMySqlSite = new DAOMySqlSite(this.formulaireAjout);
        this.daoMySqlSite.dialog.show();
    }


    @Override
    protected Void doInBackground(Site... params) {


        this.daoMySqlSite.sauver(this.site);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.daoMySqlSite.dialog.dismiss();
        this.formulaireAjout.finish();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
