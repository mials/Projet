package com.example.hani.projet.Ecouteurs;

import android.view.View;
import android.widget.Button;

import com.example.hani.projet.AffichageSite;
import com.example.hani.projet.ConfirmationSuppression;

/**
 * Created by Hani on 24/12/2015.
 */
public class EcouteurBoutonSupprimer implements Button.OnClickListener {

    AffichageSite affichageSite;
    ConfirmationSuppression confirmationSuppression;

    public EcouteurBoutonSupprimer(AffichageSite affichageSite) {
        this.affichageSite = affichageSite;
        this.confirmationSuppression = new ConfirmationSuppression(this.affichageSite);
    }

    @Override
    public void onClick(View v) {

        this.confirmationSuppression.onCreateDialog().show();
    }
}
