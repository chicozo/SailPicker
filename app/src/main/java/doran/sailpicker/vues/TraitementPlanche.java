package doran.sailpicker.vues;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import doran.sailpicker.R;
import doran.sailpicker.analyse.Decision;

/**
 * Created by doran on 24/01/2017.
 */
public class TraitementPlanche extends Activity {
    final String WINDSPEED="windspeed";
    final String RAFALES="rafales";
    final String DIRECTION="direction";
    final String POIDS="poids";
    final String TAILLE="taille";
    final String AGE="age";
    final String NIVEAU="niveau";
    final String IMAGE_NAME="nom_image";
    int windspeed,rafales,direc,pds,taille,age,niveau;
    int pStatus=0;
    int confianceRate=0;
    Decision d;
    TextView planche;
    TextView voile;
    TextView conseil;
    ImageButton plancheImage;
    ImageButton voileImage;

    private Handler handler = new Handler();

    String choixPlanche="";
    String choixVoile="";
    String choixConseil="";

    Button retour;
    Button enregistrement;

    ProgressBar confiance;
    TextView txtProgress;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.traitement);

        Intent intent = getIntent();
        if (intent!=null)
        {
            windspeed=intent.getIntExtra(WINDSPEED,10);
            rafales=intent.getIntExtra(RAFALES,10);
            direc=intent.getIntExtra(DIRECTION,10);
            pds=intent.getIntExtra(POIDS,10);
            taille=intent.getIntExtra(TAILLE,10);
            age=intent.getIntExtra(AGE,10);
            niveau=intent.getIntExtra(NIVEAU,10);
        }
        d=new Decision(windspeed,rafales,direc,niveau,pds,age,taille);
        choixPlanche=d.choixPlanche();
        choixVoile=d.choixVoile();
        choixConseil=d.conseil();

        planche=(TextView) findViewById(R.id.plancheText);
        voile=(TextView) findViewById(R.id.voileText);
        conseil=(TextView) findViewById(R.id.conseilText);
        plancheImage=(ImageButton) findViewById(R.id.plancheButton);
        voileImage=(ImageButton) findViewById(R.id.voileButton);
        confiance=(ProgressBar) findViewById(R.id.confiance);
        txtProgress=(TextView)  findViewById(R.id.txtProgress);

        planche.setText(choixPlanche);
        voile.setText(choixVoile);
        conseil.setText(choixConseil);

        retour=(Button) findViewById(R.id.retour);
        enregistrement=(Button) findViewById(R.id.enregistrer);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                confianceRate=d.confianceRate();

                while (pStatus <= confianceRate) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            confiance.setProgress(pStatus);
                            txtProgress.setText("confiance:\n"+pStatus + " %\n\n\n");
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent revenirEnArrière = new Intent(TraitementPlanche.this, MenuPlanche.class);
                finish();
                startActivity(revenirEnArrière);

            }
        });
        plancheImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent zoom=new Intent(TraitementPlanche.this,Zoom.class);
                zoom.putExtra(IMAGE_NAME,choixPlanche);
                finish();
                startActivity(zoom);
            }
        });
        voileImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent zoom=new Intent(TraitementPlanche.this,Zoom.class);
                zoom.putExtra(IMAGE_NAME,choixVoile);
                finish();
                startActivity(zoom);
            }
        });
        enregistrement.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

               /* File myFile = new File(Environment.getExternalStorageDirectory() +
                        File.separator + "appli_test","dataAnalysis.txt"); //on déclare notre futur fichier

                File myDir = new File(Environment.getExternalStorageDirectory() +
                        File.separator + "appli_test"); //pour créer le repertoire dans lequel on va mettre notre fichier
                Boolean success=true;
                if (!myDir.exists()) {
                    success = myDir.mkdir(); //On crée le répertoire (s'il n'existe pas!!)
                }
                if (success){

                    String data = ""+sex+"  "+age+" "+d.calculIMC()+"   "+niveau+"  "+windspeed+"   "+rafales+" "+direc+"   "+choixPlanche+"    "+choixVoile+"\r\n";

                    FileOutputStream output = null; //le true est pour écrire en fin de fichier, et non l'écraser
                    try {
                        output = new FileOutputStream(myFile,true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        output.write(data.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {Log.e("TEST1","ERROR DE CREATION DE DOSSIER");}
*/

                Intent revenirEnArrière = new Intent(TraitementPlanche.this, MenuPlanche.class);
                finish();
                startActivity(revenirEnArrière);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent revenirEnArrière = new Intent(TraitementPlanche.this, MenuPlanche.class);
        finish();
        startActivity(revenirEnArrière);
    }


}
