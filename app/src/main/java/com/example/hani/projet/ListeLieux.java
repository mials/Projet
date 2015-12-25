package com.example.hani.projet;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.hani.projet.DAO.DAOMySqlSite;
import com.example.hani.projet.Model.Outils;
import com.example.hani.projet.Model.Site;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ListeLieux extends ListActivity  {

    SimpleCursorAdapter mAdapter;
    ArrayList<HashMap<String, String>> listItem;
    ProgressDialog dialog;
    String json;
    String latitude , longitude;
    DAOMySqlSite daoMySqlSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_lieux);

        Intent intent = getIntent();
        if (intent != null) {
            latitude = intent.getExtras().getString("latitude");
            longitude = intent.getExtras().getString("longitude");
        }
        this.daoMySqlSite = new DAOMySqlSite(this);
        this.daoMySqlSite.dialog.show();
        this.listItem = new ArrayList<HashMap<String, String>>();
        new Charger().execute();

    }

    void afficher()
    {
        try {

            JSONObject jsonObj = new JSONObject(json);
            JSONArray peoples = jsonObj.getJSONArray(Outils.TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id_site = c.getString(Outils.TAG_ID);
                String nom = c.getString(Outils.TAG_NOM);
                String categorie = c.getString(Outils.TAG_CATEG);
                String latitude = c.getString(Outils.TAG_LATITU);
                String longitude = c.getString(Outils.TAG_LONGIT);
                String adresse = c.getString(Outils.TAG_ADD);
                String resume = c.getString(Outils.TAG_RESUM);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(Outils.TAG_ID, id_site);
                persons.put(Outils.TAG_NOM, nom);
                persons.put(Outils.TAG_CATEG, categorie);
                persons.put(Outils.TAG_LATITU, latitude);
                persons.put(Outils.TAG_LONGIT, longitude);
                persons.put(Outils.TAG_ADD, adresse);
                persons.put(Outils.TAG_RESUM, resume);
                persons.put("img", String.valueOf(R.drawable.img));

                listItem.add(persons);
            }

        }catch (Exception e)
        {
            System.out.println("erreur dans la phase de conversion json to hashmap"+e.toString());
        }
        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.affichage_list_view,
                new String[] {"img", "nom", "resume"}, new int[] {R.id.img, R.id.titre, R.id.description});

        //On attribut à notre listView l'adapter que l'on vient de créer
        this.setListAdapter(mSchedule);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_lieux, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10)
        {
            this.recreate();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        HashMap<String, String> element = new HashMap<String, String>();
        element =  (HashMap<String, String>) o;
        Site site = new Site(Integer.parseInt(element.get(Outils.TAG_ID)),
                            element.get(Outils.TAG_NOM),
                            element.get(Outils.TAG_CATEG),
                            Float.parseFloat(element.get(Outils.TAG_LATITU)),
                            Float.parseFloat(element.get(Outils.TAG_LONGIT)),
                            element.get(Outils.TAG_ADD),
                            element.get(Outils.TAG_RESUM));


        Toast.makeText(this, "You have chosen" + " "+site.getNom(), Toast.LENGTH_LONG).show();

        Intent monIntent = new Intent(this,com.example.hani.projet.AffichageSite.class);

        monIntent.putExtra("latitude", this.latitude);
        monIntent.putExtra("longitude", this.longitude);

        Bundle mBundle = new Bundle();
        mBundle.putSerializable(Outils.TAG_SITE, site);
        monIntent.putExtras(mBundle);

        this.startActivityForResult(monIntent , 10);
    }


    class Charger extends AsyncTask<Void , Void , String> {

        @Override
        protected String doInBackground(Void... params) {

            return daoMySqlSite.chargerTous();
        }


        @Override
        protected void onPostExecute(String resultat) {
            //super.onPostExecute(resultat);
            daoMySqlSite.dialog.dismiss();
            json = resultat;
            afficher();

        }
    }

}