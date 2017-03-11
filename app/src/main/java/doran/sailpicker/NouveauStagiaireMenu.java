package doran.sailpicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doran on 07/02/2017.
 */
public class NouveauStagiaireMenu extends Activity implements AdapterView.OnItemSelectedListener{
    Spinner ageSpinner,niveauSpinner,supportSpinner,poidsSpinner;

    Button retourMenu;
    Button confirmerstagiaire;
    TextView ageMenu,niveauMenu,supportMenu;
    final String AGE="age";
    final String NIVEAU="niveau";
    final String SUPPORT="support";
    final String POIDS="poids";

    //Notez qu'on utilise Menu.FIRST pour indiquer le premier élément d'un menu
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menustagiaire);
        retourMenu = (Button) findViewById(R.id.retourMenu);
        confirmerstagiaire=(Button) findViewById(R.id.confirmerStagiaire);
        ageMenu=(TextView) findViewById(R.id.ageMenu);
        niveauMenu=(TextView)findViewById(R.id.niveauMenu);
        supportMenu=(TextView)findViewById(R.id.supportMenu);
        ageSpinner=(Spinner)findViewById(R.id.spinner1);
        niveauSpinner=(Spinner)findViewById(R.id.spinner2);
        supportSpinner=(Spinner) findViewById(R.id.spinner3);
        poidsSpinner=(Spinner) findViewById(R.id.spinner4);

        ageSpinner.setOnItemSelectedListener(this);
        List<String> agecategories = new ArrayList<String>();
        agecategories.add("4 ans et demi à 5 ans et demi");
        agecategories.add("5 ans et demi à 7 ans");
        agecategories.add("7 ans à 10 ans");
        agecategories.add("10 ans à 13 ans");
        agecategories.add(">13 ans");
        agecategories.add(">15 ans");
        agecategories.add(">18 ans");


        niveauSpinner.setOnItemSelectedListener(this);
        List<String> niveaucategories = new ArrayList<String>();
        niveaucategories.add("Je ne suis jamais allé sur l'eau");
        niveaucategories.add("Niveau1: Je sais me déplacer sur un trajet choisi par le moniteur");
        niveaucategories.add("Niveau2: Je sais atteindre tout point du plan d'eau");
        niveaucategories.add("Niveau3: Je sais évolué sur une zone librement");
        niveaucategories.add("Niveau4: Je suis autonome et je sais choisir ma zone");
        niveaucategories.add("Niveau5: Je suis performant et responsable");

        supportSpinner.setOnItemSelectedListener(this);
        List<String> supportcategories = new ArrayList<String>();
        supportcategories.add("Ne sais pas");
        supportcategories.add("Planche à voile");
        supportcategories.add("Deriveur");
        supportcategories.add("Kayak");

        poidsSpinner.setOnItemSelectedListener(this);
        List<String> poidscategories = new ArrayList<String>();
        poidscategories.add("<50 kg");
        poidscategories.add(">50kg");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, agecategories);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        ageSpinner.setAdapter(dataAdapter1);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, niveaucategories);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        niveauSpinner.setAdapter(dataAdapter2);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, supportcategories);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        supportSpinner.setAdapter(dataAdapter3);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, poidscategories);

        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        poidsSpinner.setAdapter(dataAdapter4);


        retourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(NouveauStagiaireMenu.this,PremierePage.class);
                finish();
                startActivity(intent);

            }
        });
        confirmerstagiaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int age=0;
                int niveau=100;
                int support=0;
                int poids=0;
                if(((String)ageSpinner.getSelectedItem()).equals("4 ans et demi à 5 ans et demi"))
                {
                    age=1;
                }
                else if (((String)ageSpinner.getSelectedItem()).equals("5 ans et demi à 7 ans"))
                {
                    age=2;
                }
                else if(((String)ageSpinner.getSelectedItem()).equals("7 ans à 10 ans"))
                {
                    age=3;
                }
                else if(((String)ageSpinner.getSelectedItem()).equals("10 ans à 13 ans"))
                {
                    age=4;
                }
                else if(((String)ageSpinner.getSelectedItem()).equals(">13 ans"))
                {
                    age=5;
                }
                else if(((String)ageSpinner.getSelectedItem()).equals(">15 ans"))
                {
                    age=6;
                }
                else{
                    age=7;
                }
                if(niveauSpinner.getSelectedItem().equals("Je ne suis jamais allé sur l'eau"))
                {
                    niveau=0;
                }
                else if((niveauSpinner.getSelectedItem()).equals("Niveau1: Je sais me déplacer sur un trajet choisi par le moniteur"))
                {
                    niveau=1;
                }
                else if((niveauSpinner.getSelectedItem()).equals("Niveau2: Je sais atteindre tout point du plan d'eau"))
                {
                    niveau=2;
                }
                else if((niveauSpinner.getSelectedItem()).equals("Niveau3: Je sais évolué sur une zone librement"))
                {
                    niveau=3;
                }
                else if((niveauSpinner.getSelectedItem()).equals("Niveau4: Je suis autonome et je sais choisir ma zone"))
                {
                    niveau=4;
                }
                else{
                    niveau=5;
                }
                if(supportSpinner.getSelectedItem().equals("Ne sais pas"))
                {
                    support=1;
                }
                else if((supportSpinner.getSelectedItem()).equals("Planche à voile"))
                {
                    support=2;
                }
                else if((supportSpinner.getSelectedItem()).equals("Deriveur"))
                {
                    support=3;
                }
                else
                {
                    support=4;
                }
                if(poidsSpinner.getSelectedItem().equals("<50kg"))
                {
                    poids=1;
                }
                else{
                    poids=2;
                }
                Intent intent= new Intent(NouveauStagiaireMenu.this,TraitementNouveauStagiaire.class);
                intent.putExtra(AGE,age);
                intent.putExtra(NIVEAU, niveau);
                intent.putExtra(SUPPORT,support);
                intent.putExtra(POIDS,poids);
                finish();
                startActivity(intent);

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(NouveauStagiaireMenu.this,PremierePage.class);
        finish();
        startActivity(intent);
    }
}

