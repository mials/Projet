package com.example.hani.projet.Ecouteurs;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.hani.projet.MapsActivity;

/**
 * Created by slaim1u on 03/12/2015.
 */
public class EcouteurMesLieux implements Button.OnClickListener {

    private MapsActivity maps;

    public EcouteurMesLieux(MapsActivity maps) {
        this.maps = maps;
    }

    @Override
    public void onClick(View v) {
        Intent monIntent = new Intent(this.maps,com.example.hani.projet.MaListe.class);
        this.maps.startActivity(monIntent);
    }
}
