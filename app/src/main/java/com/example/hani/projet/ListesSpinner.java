package com.example.hani.projet;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hani on 27/11/2015.
 */
public class ListesSpinner implements AdapterView.OnItemSelectedListener {


    Spinner categorier , rayon;
    MapsActivity activity;

    public ListesSpinner(MapsActivity activity) {
        this.activity = activity;

        categorier = (Spinner) this.activity.findViewById(R.id.Catégorie);
        ArrayAdapter adapterCategorie = ArrayAdapter.createFromResource(this.activity, R.array.Catégorie,R.layout.spinner_item );
        categorier.setAdapter(adapterCategorie );
        categorier.setOnItemSelectedListener(this);

        rayon = (Spinner) this.activity.findViewById(R.id.Rayon);
        ArrayAdapter adapterRayon = ArrayAdapter.createFromResource(this.activity, R.array.Rayon,R.layout.spinner_item );
        rayon.setAdapter(adapterRayon);
        rayon.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView text = (TextView) view;
        Toast.makeText(this.activity, "vous avez selectionné " + text.getText(), Toast.LENGTH_SHORT).show();
        this.activity.getLocation().initCateRay(this.categorier.getSelectedItem().toString() , this.rayon.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

