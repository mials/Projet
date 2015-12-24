package com.example.hani.projet.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hani.projet.Model.Site;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hani on 13/12/2015.
 */
public class DAOMySqlSite implements DAOMySql {

    ProgressDialog dialog;
    ArrayList<HashMap<String, String>> listSites; // utilisé juste dans la fonction chargertous pour contenir tous les sites qui sont dans la base de donnees
    public final static String Server = "http://www.exemple.com/";

    public DAOMySqlSite(Context context) {
        this.dialog = new ProgressDialog(context );
        this.dialog.setCancelable(false);
        this.dialog.setTitle("Progression...");
        this.dialog.setMessage("Patientez...");

    }


    @Override
    public void sauver(Site site) {
        this.dialog.show();
        new SauverSite(site , this).execute();


    }

    @Override
    public Site charger(int i) {

        try {
            String myurl= "http://www.exemple.com/getPersonnes";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            //String result = Outils.InputStreamToStringDefault(inputStream);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject("");
            // On récupère le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(jsonObject.getString("personnes"));
            // Pour tous les objets on récupère les infos
            for (int j = 0; j < array.length(); j++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne la liste des personnes
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> chargerTous() {
        new ChargerSites(this).execute();
        return this.listSites;
    }

    @Override
    public ArrayList<Site> chargerSelonCategorie(String categorie, float latitude, float longitude) {
        return null;
    }


}
