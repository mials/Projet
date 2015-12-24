package com.example.hani.projet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Site;

import java.util.ArrayList;

/**
 * Created by Hani on 24/12/2015.
 */
public class ConfirmationSuppression {

    AffichageSite affichageSite;
    DAOMySqlSite daoMySqlSite;

    public ConfirmationSuppression(AffichageSite affichageSite) {
        this.affichageSite = affichageSite;
        this.daoMySqlSite = new DAOMySqlSite(this.affichageSite);
    }


    public Dialog onCreateDialog() {
    // Création d'un boite de dialogue
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.affichageSite);

        builder.setMessage("Voulez-vous supprimer le site?");
        builder.setCancelable(false);
        builder.setTitle("Confirmation");

        builder.setPositiveButton("OUI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new Supprimer().execute();
                    }
                });

        builder.setNegativeButton("NON",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        dialog = builder.create();
        return dialog;
    }




    public class Supprimer extends AsyncTask<Void , Void , Void> {


        @Override
        protected Void doInBackground(Void... params) {
            daoMySqlSite.supprimerSite(affichageSite.getSiteAffiche().getId());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent monIntent = new Intent(affichageSite,com.example.hani.projet.MapsActivity.class);
            affichageSite.startActivityForResult(monIntent , 1);
        }
    }
}
