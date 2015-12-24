package com.example.hani.projet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hani.projet.Ecouteurs.EcouteurBoutonPoster;

public class FormulaireAjout extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnercategorier;
    EditText nom, adresse , resume , longitude , latitude;
    EcouteurBoutonPoster ecouteurBoutonPoster;
    Button poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_ajout);


        spinnercategorier = (Spinner) this.findViewById(R.id.spinner);
        ArrayAdapter adapterCategorie = ArrayAdapter.createFromResource(this, R.array.Catégorie,R.layout.spinner_item );
        spinnercategorier.setAdapter(adapterCategorie);
        spinnercategorier.setOnItemSelectedListener(this);


        this.nom = (EditText) this.findViewById(R.id.nom);
        this.adresse = (EditText) this.findViewById(R.id.adresse);
        this.resume = (EditText) this.findViewById(R.id.resume);
        this.longitude = (EditText) this.findViewById(R.id.longitude);
        this.latitude = (EditText) this.findViewById(R.id.latitude);

        this.ecouteurBoutonPoster = new EcouteurBoutonPoster(this);
        this.poster = (Button) this.findViewById(R.id.poster);
        this.poster.setOnClickListener(this.ecouteurBoutonPoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulaire_ajout, menu);
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
        TextView text = (TextView) view;
        Toast.makeText(this, "vous avez selectionné " + text.getText(), Toast.LENGTH_SHORT).show();
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
