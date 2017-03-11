package doran.sailpicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by doran on 25/01/2017.
 */
public class Zoom extends AppCompatActivity {
    final String IMAGE_NAME="nom_image";
    String nomIm="";
    ImageButton but;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom);
        Intent intent = getIntent();
        if (intent!=null)
        {
           nomIm=intent.getStringExtra(IMAGE_NAME);
        }
        but=(ImageButton)findViewById(R.id.affiche);
        int id = getApplication().getResources().getIdentifier(nomIm, "drawable", getApplication().getPackageName());
        but.setImageResource(id);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent revenirEnArrière = new Intent(Zoom.this, TraitementPlanche.class);
                finish();
                startActivity(revenirEnArrière);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent revenirEnArrière = new Intent(Zoom.this, TraitementPlanche.class);
        finish();
        startActivity(revenirEnArrière);
    }
}
