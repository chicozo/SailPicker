package doran.sailpicker.vues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import doran.sailpicker.R;
import doran.sailpicker.analyse.DecisionNouveauStagiaire;

/**
 * Created by doran on 11/02/2017.
 */
public class TraitementNouveauStagiaire extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    final String AGE="age";
    final String NIVEAU="niveau";
    final String SUPPORT="support";
    final String POIDS="poids";
    YouTubePlayerView youTubeView;
    int age,niveau,support,pds;
    DecisionNouveauStagiaire n;
    Button retour;
    TextView test;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traitementstagiaire);
        Intent intent = getIntent();
        test=(TextView)findViewById(R.id.text);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player2);
        youTubeView.initialize("API KEY", this);
        if (intent!=null)
        {

            age=intent.getIntExtra(AGE,0);
            niveau=intent.getIntExtra(NIVEAU,0);
            support=intent.getIntExtra(SUPPORT,0);
            pds=intent.getIntExtra(POIDS,0);
            n = new DecisionNouveauStagiaire(age,niveau,pds,support);
            test.setText(n.resolution());
        }
        retour=(Button)findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent revenirEnArrière = new Intent(TraitementNouveauStagiaire.this, NouveauStagiaireMenu.class);
                finish();
                startActivity(revenirEnArrière);

            }
        });


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b) {
            youTubePlayer.cueVideo(n.getvidUrl()); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(TraitementNouveauStagiaire.this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed() {
        Intent revenirEnArrière = new Intent(TraitementNouveauStagiaire.this, NouveauStagiaireMenu.class);
        finish();
        startActivity(revenirEnArrière);
    }
}
