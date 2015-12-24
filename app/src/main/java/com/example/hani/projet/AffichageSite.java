package com.example.hani.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hani.projet.Ecouteurs.EcouteurBoutonAjouter;
import com.example.hani.projet.Ecouteurs.EcouteurBoutonSupprimer;
import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;

public class AffichageSite extends AppCompatActivity {

    TextView categorie , latitude , longitude, resume, adresse, nom;
    ImageView img;
    Button ajouter , supprimer , modifier;
    String latitudeCourante, longitudeCourante;
    Site siteAffiche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_site);

        this.ajouter = (Button) findViewById(R.id.ajouter);
        this.ajouter.setOnClickListener(new EcouteurBoutonAjouter(this));
        this.supprimer = (Button) findViewById(R.id.supprimer);
        this.supprimer.setOnClickListener(new EcouteurBoutonSupprimer(this));

        Intent intent = getIntent();
        if (intent != null) {
            this.siteAffiche = (Site) intent.getSerializableExtra(Outils.TAG_SITE);
            latitudeCourante = intent.getExtras().getString("latitude");
            longitudeCourante = intent.getExtras().getString("longitude");


            this.categorie = (TextView) findViewById(R.id.categorie);
            this.latitude = (TextView) findViewById(R.id.latitude);
            this.longitude = (TextView) findViewById(R.id.longitude);
            this.adresse = (TextView) findViewById(R.id.adresse);
            this.resume = (TextView) findViewById(R.id.resume);
            this.nom = (TextView) findViewById(R.id.Site);
            this.img = (ImageView) findViewById(R.id.imageView);

            this.categorie.setText(this.siteAffiche.getCategorie());
            this.latitude.setText(""+this.siteAffiche.getLatitude());
            this.longitude.setText("" +this.siteAffiche.getLongitude());
            this.adresse.setText(this.siteAffiche.getAdresse());
            this.nom.setText(this.siteAffiche.getNom());
            this.resume.setText(this.siteAffiche.getResume());
            this.img.setImageResource(R.drawable.img);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_affichage_site, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getLatitudeCourante() {
        return latitudeCourante;
    }

    public String getLongitudeCourante() {
        return longitudeCourante;
    }

    public Site getSiteAffiche() {
        return siteAffiche;
    }
}
