package com.example.hani.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;

public class AffichageSite extends AppCompatActivity {

    TextView categorie , latitude , longitude, resume, adresse, nom;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_site);

        Intent intent = getIntent();
        if (intent != null) {
            Site site = (Site) intent.getSerializableExtra(Outils.TAG_SITE);


            this.categorie = (TextView) findViewById(R.id.categorie);
            this.latitude = (TextView) findViewById(R.id.latitude);
            this.longitude = (TextView) findViewById(R.id.longitude);
            this.adresse = (TextView) findViewById(R.id.adresse);
            this.resume = (TextView) findViewById(R.id.resume);
            this.nom = (TextView) findViewById(R.id.Site);
            this.img = (ImageView) findViewById(R.id.imageView);

            this.categorie.setText(site.getCategorie());
            this.latitude.setText(""+site.getLatitude());
            this.longitude.setText(""+site.getLongitude());
            this.adresse.setText(site.getAdresse());
            this.nom.setText(site.getNom());
            this.resume.setText(site.getResume());
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
}
