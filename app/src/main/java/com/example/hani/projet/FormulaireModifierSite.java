package com.example.hani.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hani.projet.Ecouteurs.EcouteurBoutonValider;
import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;

public class FormulaireModifierSite extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button modifier;
    Spinner spinnercategorier;
    EditText nom, adresse , resume , longitude , latitude;
    public Site site;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_site);

        spinnercategorier = (Spinner) this.findViewById(R.id.spinner);
        ArrayAdapter adapterCategorie = ArrayAdapter.createFromResource(this, R.array.Catégorie,R.layout.spinner_item );
        spinnercategorier.setAdapter(adapterCategorie);
        spinnercategorier.setOnItemSelectedListener(this);

        this.nom = (EditText) this.findViewById(R.id.nomM);
        this.adresse = (EditText) this.findViewById(R.id.adresseM);
        this.resume = (EditText) this.findViewById(R.id.resumeModif);
        this.longitude = (EditText) this.findViewById(R.id.longitudeM);
        this.latitude = (EditText) this.findViewById(R.id.latitudeM);

        Intent intent = getIntent();
        if (intent != null) {
            site = (Site) intent.getSerializableExtra(Outils.TAG_SITE);

            this.nom.setText(site.getNom());
            this.adresse.setText(site.getAdresse());
            this.resume.setText(site.getResume());
            this.longitude.setText(""+site.getLongitude());
            this.latitude.setText(""+site.getLongitude());

        }

        this.modifier = (Button) findViewById(R.id.modifierBouton);
        this.modifier.setOnClickListener(new EcouteurBoutonValider(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ma_liste, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Spinner getSpinnercategorier() {
        return spinnercategorier;
    }

    public EditText getNom() {
        return nom;
    }

    public EditText getAdresse() {
        return adresse;
    }

    public EditText getResume() {
        return resume;
    }

    public EditText getLongitude() {
        return longitude;
    }

    public EditText getLatitude() {
        return latitude;
    }
}
