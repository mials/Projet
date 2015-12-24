package com.example.hani.projet.DAO;

import android.os.AsyncTask;

import com.example.hani.projet.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hani on 21/12/2015.
 */
public class ChargerSites extends AsyncTask<Void , Void , String> {

    DAOMySqlSite daoMySqlSite;
    // variables static qui represenatant les attribues de Site
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id_site";
    private static final String TAG_NOM = "nom";
    private static final String TAG_ADD ="adresse";
    private static final String TAG_CATEG="categorie";
    private static final String TAG_LATITU = "latitude";
    private static final String TAG_LONGIT = "longitude";
    private static final String TAG_RESUM ="resume";

    public ChargerSites(DAOMySqlSite daoMySqlSite) {
        this.daoMySqlSite = daoMySqlSite;
        this.daoMySqlSite.listSites = new ArrayList<HashMap<String, String>>() ;
    }


    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection connection;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("http://pandroid.esy.es/chargerSites.php");
            connection = (HttpURLConnection) url.openConnection();
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

        return result.toString();
    }


    @Override
    protected void onPostExecute(String resultat) {
        super.onPostExecute(resultat);

        try {
            JSONObject jsonObj = new JSONObject(resultat);
            JSONArray peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id_site = c.getString(TAG_ID);
                String nom = c.getString(TAG_NOM);
                String categorie = c.getString(TAG_CATEG);
                String latitude = c.getString(TAG_LATITU);
                String longitude = c.getString(TAG_LONGIT);
                String adresse = c.getString(TAG_ADD);
                String resume = c.getString(TAG_RESUM);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id_site);
                persons.put(TAG_NOM, nom);
                persons.put(TAG_CATEG, categorie);
                persons.put(TAG_LATITU, latitude);
                persons.put(TAG_LONGIT, longitude);
                persons.put(TAG_ADD, adresse);
                persons.put(TAG_RESUM, resume);
                persons.put("img", String.valueOf(R.drawable.img));

                this.daoMySqlSite.listSites.add(persons);
            }

        }catch (Exception e)
        {
            System.out.println("********erreur dans la phase de conversion json to hashmap*********");
        }
    }
}
