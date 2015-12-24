package com.example.hani.projet.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hani.projet.Model.Site;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    @Override
    public void supprimerSite(int i) {

        HttpURLConnection connection;
        OutputStreamWriter request = null;

        URL url = null;
        String response = null;
        String parameters = "id_site="+i;
        try
        {
            url = new URL("http://pandroid.esy.es/supprimerSite.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();

            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            // Response from server after login process will be stored in response variable.
            response = sb.toString();
            // You can perform UI operations here
            //Toast.makeText("Message from Server").show();
            isr.close();
            reader.close();

        }
        catch(IOException e)
        {
            // Error
        }

    }


}
