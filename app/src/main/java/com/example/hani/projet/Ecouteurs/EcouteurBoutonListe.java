package com.example.hani.projet.Ecouteurs;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.hani.projet.MapsActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hani on 01/12/2015.
 */
public class EcouteurBoutonListe implements Button.OnClickListener {
    private MapsActivity maps;

    public EcouteurBoutonListe(MapsActivity maps) {
        this.maps = maps;
    }

    @Override
    public void onClick(View v) {
        //this.maps.onResume();
        Intent monIntent = new Intent(this.maps,com.example.hani.projet.ListeLieux.class);
        this.maps.startActivity(monIntent);

    }
}
