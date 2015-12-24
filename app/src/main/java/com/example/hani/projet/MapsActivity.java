package com.example.hani.projet;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hani.projet.Boutons;
import com.example.hani.projet.DAO.ChargerSiteSelonCategorie;
import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.ListesSpinner;
import com.example.hani.projet.Localisation;
import com.example.hani.projet.MaListe;
import com.example.hani.projet.Model.Site;
import com.example.hani.projet.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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


public class MapsActivity extends FragmentActivity {


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ListesSpinner listeSpinner;
    private LocationListener location;
    private Boutons boutons;
    private MaListe mesLieux;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.location = new Localisation(this);
        this.boutons = new Boutons(this);
        this.listeSpinner = new ListesSpinner(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 10, this.location);
        } catch (Exception e) {
        }


        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    private void setUpMap() {

        CameraUpdate camera1 = CameraUpdateFactory.zoomTo(13);

        this.mMap.moveCamera(camera1);

        //CameraUpdate camera = CameraUpdateFactory.zoomTo(10);

       // CameraUpdate camera1 = CameraUpdateFactory.newLatLng(new LatLng(this.location.getLatitude(), this.location.getLongitude()));

        //mMap.moveCamera(camera1);
        //mMap.animateCamera(camera1, 2000, null);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(this.location.getLatitude(), this.location.getLongitude())).title("Marker"));

        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLongitude(), mMap.getMyLocation().getLatitude()), 105));
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());//newLatLngZoom(mMap.getMyLocation(), 15));



        //CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(0,0)).zoom(100).bearing(90).tilt(30).build();
        //CameraUpdateFactory.newCameraPosition(cameraPosition);


    }



    public GoogleMap getmMap() {
        return mMap;
    }


    public ListesSpinner getListeSpinner() {
        return listeSpinner;
    }

    public Localisation getLocation() {
        return (Localisation) location;
    }
}
