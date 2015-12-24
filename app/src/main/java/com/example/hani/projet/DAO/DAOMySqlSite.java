package com.example.hani.projet.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hani.projet.Model.Outils;
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

    public ProgressDialog dialog;
    ArrayList<HashMap<String, String>> listSites; // utilisé juste dans la fonction chargertous pour contenir tous les sites qui sont dans la base de donnees

    public DAOMySqlSite(Context context) {
        this.dialog = new ProgressDialog(context );
        this.dialog.setCancelable(false);
        this.dialog.setTitle("Progression...");
        this.dialog.setMessage("Patientez...");

    }

    @Override
    public void sauver(Site site) {

        HttpURLConnection connection;
        OutputStreamWriter request = null;

        URL url = null;
        String response = null;
        String parameters = "nom="+site.getNom()+"&categorie="+site.getCategorie()+"&latitude="+site.getLatitude()+"&longitude="+site.getLongitude()+"&adresse="+site.getAdresse()+"&resume="+site.getResume();
        try
        {
            url = new URL("http://pandroid.esy.es/sauverSite.php");
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

        //this.dialog.show();
        //new SauverSite(site , this).execute();


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
    public String chargerTous() {
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
    public ArrayList<Site> chargerSelonCategorie(String categorie, String latitudeMin ,String latitudeMax, String longitudeMin , String longitudeMax) {
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


// outils // fonctions internes
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
