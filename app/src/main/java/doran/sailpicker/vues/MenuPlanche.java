package doran.sailpicker.vues;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import doran.sailpicker.R;
import doran.sailpicker.meteo.MeteoConverter;
import doran.sailpicker.meteo.WBTraitement;
import doran.sailpicker.meteo.WindguruTraitement;

public class MenuPlanche extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //meteo Converter
    MeteoConverter m;

    //chargement
    protected ProgressDialog mProgressDialog;
    private Context mContext;
    //declaration des zones de texte editables
    EditText age;
    EditText poids;
    EditText niveau;
    EditText taille;
    //recupération de la page
    String chaine="";
    //traitement
    WindguruTraitement mt;
    WBTraitement mt2;
    //declaration des identifiers pour le changement de classe
    final String WINDSPEED="windspeed";
    final String RAFALES="rafales";
    final String DIRECTION="direction";
    final String POIDS="poids";
    final String TAILLE="taille";
    final String AGE="age";
    final String NIVEAU="niveau";
    //declaration des tableaux contenant les differentes informations récupérées sur windguru
    int[] windspeed;
    int[] winddirectory;
    int[] rafales;
    int WBWindspeed;
    String WBDirec;
    int a;
    int pds;
    int niv;
    int taillesUser;
    Button confirmer;
    Button retourMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuplanche);
        taille = (EditText) findViewById(R.id.taille);
        age = (EditText) findViewById(R.id.ageMenu);
        poids = (EditText) findViewById(R.id.poids);
        niveau = (EditText) findViewById(R.id.niveau);
        confirmer = (Button) findViewById(R.id.confirmer);
        retourMenu=(Button) findViewById(R.id.retourMenu);
        //recupération du code de la page windguru

        GetHtmlCode taskWindguru = new GetHtmlCode();
        //GetHtmlCode taskWB = new GetHtmlCode();
        if (!isOnline()) {
            Toast.makeText(MenuPlanche.this, "connectez vous puis revenez",
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(MenuPlanche.this, "retour au menu...",
                    Toast.LENGTH_LONG).show();
            Handler h = new Handler();
            h.postDelayed(r, 3000);
        } else {
            taskWindguru.execute("https://www.windguru.cz/48425");

            compute();
            Thread traitementMeteo = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2500);
                        mt=new WindguruTraitement();
                        Thread.sleep(500);
                        mt.traitement(chaine);
                        Thread.sleep(500);
                        windspeed= mt.getWindspeed();
                        winddirectory=mt.getDirec();
                        rafales=mt.getRafales();


                        /*taskWB.execute("http://www.windsurfbreizh22.com/anemo2/public/anemo.php?interval=1&id=trega");
                        Thread.sleep(250);
                        mt2=new WBTraitement();
                        Thread.sleep(250);
                        mt2.traitementWB(chaine);
                        Thread.sleep(250);
                        WBWindspeed=mt2.getWBwindspeed();
                        WBDirec=mt2.getWBDirec();*/
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(windspeed!=null) {
                                    m = new MeteoConverter(windspeed[0], rafales[0], winddirectory[0]);
                                    // update TextView here!
                                    Toast.makeText(MenuPlanche.this, m.toString() + "\n",
                                            Toast.LENGTH_LONG).show();
                                }
                                else{Toast.makeText(MenuPlanche.this, "Problème de connection",
                                        Toast.LENGTH_LONG).show();}

                            }
                        });

                    } catch (InterruptedException e) {
                    }
                }
            };
            traitementMeteo.start();
            //execution du bouton supprimer

            confirmer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a = Integer.parseInt(age.getText().toString());
                    pds = Integer.parseInt(poids.getText().toString());
                    niv = Integer.parseInt(niveau.getText().toString());
                    taillesUser = Integer.parseInt(taille.getText().toString());
                    Intent intent = new Intent(MenuPlanche.this, TraitementPlanche.class);
                    intent.putExtra(WINDSPEED, windspeed[0]);
                    intent.putExtra(RAFALES, rafales[0]);
                    intent.putExtra(DIRECTION, winddirectory[0]);
                    intent.putExtra(TAILLE, taillesUser);
                    intent.putExtra(AGE, a);
                    intent.putExtra(POIDS, pds);
                    intent.putExtra(NIVEAU, niv);
                    finish();
                    startActivity(intent);


                }
            });
        }
        retourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(MenuPlanche.this,PremierePage.class);
                finish();
                startActivity(intent);

            }
        });
    }
    //fermeture de l'app
    Runnable r = new Runnable() {
        @Override
        public void run(){
            Intent intent=new Intent(MenuPlanche.this,PremierePage.class);
            finish();
            startActivity(intent);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //classe permettant de récupérer le code de la page windguru
        private class GetHtmlCode extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                URL siteUrl = null;
                URLConnection siteConnection;
                BufferedReader in;
                String codeHtml = null;
                String rl = null;

                try {
                    siteUrl = new URL(params[0]);
                    siteConnection = siteUrl.openConnection();
                    in = new BufferedReader(new InputStreamReader(siteConnection.getInputStream()));

                    codeHtml = "";
                    // Récupération du code HTML du site ligne par ligne
                    while ((rl = in.readLine()) != null) {
                        codeHtml += rl;

                        // Test si le l'AsyncTask est cancel pour annuler
                        // la lecture du site
                        if (isCancelled()) {
                            in.close();
                            return null;
                        }
                    }

                    // Fermeture du BufferReader
                    in.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return codeHtml;
            }

            @Override
            protected void onPostExecute(String result) {
                // result contient le code html

                chaine = result;
                Toast.makeText(MenuPlanche.this, chaine,
                        Toast.LENGTH_LONG).show();
            }

        }


    private void compute() {
        mProgressDialog = ProgressDialog.show(this, "Please wait",
                "Refreshing data...", true);

        // useful code, variables declarations...
        new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    mProgressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        })).start();

        // ...
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(MenuPlanche.this,PremierePage.class);
        finish();
        startActivity(intent);
    }
}

