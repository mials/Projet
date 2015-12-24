package com.example.hani.projet.DAO;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.hani.projet.Model.Site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hani on 13/12/2015.
 */
public class SauverSite extends AsyncTask <Site , Void , Void> {

    public Site site;
    public DAOMySqlSite daoMySqlSite;

    public SauverSite(Site site, DAOMySqlSite daoMySqlSite) {
        this.site = site;
        this.daoMySqlSite = daoMySqlSite;
    }


    @Override
    protected Void doInBackground(Site... params) {
        HttpURLConnection connection;
        OutputStreamWriter request = null;

        URL url = null;
        String response = null;
        String parameters = "nom="+this.site.getNom()+"&categorie="+this.site.getCategorie()+"&latitude="+this.site.getLatitude()+"&longitude="+this.site.getLongitude()+"&adresse="+this.site.getAdresse()+"&resume="+this.site.getResume();
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

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.daoMySqlSite.dialog.dismiss();
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
