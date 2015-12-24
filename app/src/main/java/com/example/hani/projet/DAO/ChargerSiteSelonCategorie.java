package com.example.hani.projet.DAO;

import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;
import com.example.hani.projet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hani on 23/12/2015.
 */
public class ChargerSiteSelonCategorie {

    public ChargerSiteSelonCategorie()
    {}

    public ArrayList<Site> charger(String categorie, String latitudeMin ,String latitudeMax, String longitudeMin , String longitudeMax)
    {
        HttpURLConnection connection;
        StringBuilder result = new StringBuilder();
        String parameters = "categorie="+categorie+"&latitudeMin="+latitudeMin+"&longitudeMin="+longitudeMin+"&latitudeMax="+latitudeMax+"&longitudeMax="+longitudeMax;

        try {
            URL url = new URL("http://pandroid.esy.es/chargerSitesSelonCategorie.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            //connection.disconnect();
        }

        return this.stringToListSite(result.toString());
    }


    public ArrayList<Site> stringToListSite(String resultat)
    {
        ArrayList<Site> resultatSites = new ArrayList<Site>();

        try {

            JSONObject jsonObj = new JSONObject(resultat);
            JSONArray peoples = jsonObj.getJSONArray(Outils.TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id_site = c.getString(Outils.TAG_ID);
                String nom = c.getString(Outils.TAG_NOM);
                String categorie = c.getString(Outils.TAG_CATEG);
                String latitude = c.getString(Outils.TAG_LATITU);
                String longitude = c.getString(Outils.TAG_LONGIT);
                String adresse = c.getString(Outils.TAG_ADD);
                String resume = c.getString(Outils.TAG_RESUM);

                resultatSites.add(new Site(Integer.parseInt(id_site),nom,categorie,Float.parseFloat(latitude),Float.parseFloat(longitude),adresse,resume));
            }

        }catch (Exception e)
        {
            System.out.println("erreur dans la phase de conversion "+e.toString());
        }
        return resultatSites;
    }
}
