package com.example.hani.projet;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hani.projet.DAO.ChargerSiteSelonCategorie;
import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hani on 01/12/2015.
 */
public class Localisation implements LocationListener {


    private double latitude , longitude;
    String latitudeMax, latitudeMin , longitudeMax, longitudeMin , categorie;
    String strLongitude,strLatitude;
    double rayon;
    private MapsActivity maps;
    ChargerSiteSelonCategorie chargerSiteSelonCategorie;
    Location location;

    public Localisation(MapsActivity maps) {
        this.maps = maps;
        this.chargerSiteSelonCategorie = new ChargerSiteSelonCategorie();
    }

    @Override
    public void onLocationChanged(Location location){
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        strLongitude = location.convert(longitude, location.FORMAT_DEGREES);
        this.location = location;

        CameraUpdate camera1 = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        this.maps.getmMap().moveCamera(camera1);

        this.initCateRay(this.maps.getListeSpinner().categorier.getSelectedItem().toString(), this.maps.getListeSpinner().rayon.getSelectedItem().toString());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void initCateRay(String categorie , String rayon)
    {
        this.categorie = categorie;
        this.rayon = Double.parseDouble(rayon);
        latitudeMin = ""+(latitude - this.rayon/ Outils.LATITUDEKM);
        latitudeMax = ""+(latitude + this.rayon/Outils.LATITUDEKM);
        longitudeMin = ""+(longitude - this.rayon/Outils.LONGITUDEKM);
        longitudeMax = ""+(longitude + this.rayon/Outils.LONGITUDEKM);

        new Charger().execute();
    }

    public void affichageSite(ArrayList<Site> resultat)
    {
        this.maps.getmMap().clear();
        Location loc = new Location("");
        for (Site site : resultat) {
            loc.setLatitude(site.getLatitude());
            loc.setLongitude(site.getLongitude());
            this.maps.getmMap().addMarker(new MarkerOptions().position(new LatLng(site.getLatitude(), site.getLongitude())).title(site.getNom()).snippet("Distance :"+this.location.distanceTo(loc)+"M"));
        }
    }


    public class Charger extends AsyncTask<Void , Void , ArrayList<Site>> {

        @Override
        protected ArrayList<Site> doInBackground(Void... params) {

            return chargerSiteSelonCategorie.charger(categorie , latitudeMin , latitudeMax , longitudeMin, longitudeMax);
        }


        @Override
        protected void onPostExecute(ArrayList<Site> resultat) {
            super.onPostExecute(resultat);
            affichageSite(resultat);

        }
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}



