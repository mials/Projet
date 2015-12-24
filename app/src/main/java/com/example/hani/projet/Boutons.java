package com.example.hani.projet;

import android.widget.Button;

import com.example.hani.projet.Ecouteurs.EcouteurBoutonAjouter;
import com.example.hani.projet.Ecouteurs.EcouteurBoutonListe;
import com.example.hani.projet.Ecouteurs.EcouteurMesLieux;

/**
 * Created by Hani on 01/12/2015.
 */
public class Boutons {
    private MapsActivity maps;
    private Button liste ,quitter;
    private EcouteurBoutonListe ecouteurListe;
    private EcouteurMesLieux ecouteurMesLieux;
    private EcouteurBoutonAjouter ecouteurAjouter;

    public Boutons(MapsActivity maps ) {

        this.maps = maps;

        this.ecouteurListe = new EcouteurBoutonListe(this.maps);
        this.ecouteurMesLieux = new EcouteurMesLieux(this.maps);


        liste = (Button) this.maps.findViewById(R.id.list);

        liste.setOnClickListener(this.ecouteurListe);
        //maListe.setOnClickListener(this.ecouteurMesLieux);
        //ajouter.setOnClickListener(this.ecouteurAjouter);

    }


    public void initialisation()
    {
        liste = (Button) this.maps.findViewById(R.id.list);
        liste.setOnClickListener(this.ecouteurListe);
    }
}
