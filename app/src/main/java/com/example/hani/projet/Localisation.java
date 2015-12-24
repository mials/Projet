package com.example.hani.projet;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Hani on 01/12/2015.
 */
public class Localisation implements LocationListener {


    private double latitude , longitude;
    String latitudeMax, latitudeMin , longitudeMax, longitudeMin , categorie;
    String strLongitude,strLatitude;
    double rayon;
    private MapsActivity maps;
    Location location;
    DAOMySqlSite daoMySqlSite;

    public Localisation(MapsActivity maps) {
        this.maps = maps;
        this.daoMySqlSite = new DAOMySqlSite(this.maps);
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
            this.maps.getmMap().addMarker(new MarkerOptions().position(new LatLng(site.getLatitude(), site.getLongitude())).title(site.getNom()).snippet("Distance :"+this.location.distanceTo(loc)+"M"+"\n"+site.getResume()));
        }
    }


    public class Charger extends AsyncTask<Void , Void , ArrayList<Site>> {

        @Override
        protected ArrayList<Site> doInBackground(Void... params) {

            return daoMySqlSite.chargerSelonCategorie(categorie , latitudeMin , latitudeMax , longitudeMin, longitudeMax);
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



