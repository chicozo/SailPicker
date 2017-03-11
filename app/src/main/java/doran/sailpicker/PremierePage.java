package doran.sailpicker;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;
import android.view.Menu;
/**
 * Created by doran on 07/02/2017.
 */
public class PremierePage extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubeView;
    WebView webView;
    TextView titre;
    TextView txt1;
    ImageView animationTarget;
    Button navButt;
    FlowingDrawer dr;
    ListView listvue;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        String menu[]={"Tarifs","A propos","Inscription","Nouveaux stagiaires","Stagiaires planche"};
        animationTarget = (ImageView) this.findViewById(R.id.animsun);
        listvue=(ListView)findViewById(R.id.vue);
        dr=(FlowingDrawer)findViewById(R.id.drawerlayout);
        txt1=(TextView)findViewById(R.id.textView1);
        titre=(TextView)findViewById(R.id.title);
        navButt=(Button)findViewById(R.id.NavButton);
        //mise en place des choses dans les layouts
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(75);
        webView.loadUrl("http://www.windsurfbreizh22.com/webcamHD/webcam-tregastel/image.php");
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubeView.initialize("API KEY", this);
        //police d'ecritures
        setFont(titre,"Amontillados.ttf");
        setFont(txt1,"Amontillados.ttf");
        //animation du soleil
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_around_center_point);
        animationTarget.startAnimation(animation);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);

        listvue.setAdapter(adapter);
        registerForContextMenu(listvue);
        listvue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                switch (position) {
                                                    case 0:
                                                        String url2 = "http://www.cntregastel.com/_media/flyer-club-nautique-3-plis.pdf";
                                                        Intent i2 = new Intent(Intent.ACTION_VIEW);
                                                        i2.setData(Uri.parse(url2));
                                                        startActivity(i2);
                                                        break;
                                                    case 1:
                                                        Intent intent= new Intent(PremierePage.this,About.class);
                                                        startActivity(intent);
                                                        break;
                                                    case 2:
                                                        String url = "http://www.cntregastel.com/je-minscris2.html";
                                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                                        i.setData(Uri.parse(url));
                                                        startActivity(i);
                                                        break;
                                                    case 3:
                                                        intent= new Intent(PremierePage.this,NouveauStagiaireMenu.class);
                                                        finish();
                                                        startActivity(intent);
                                                        break;
                                                    case 4:
                                                        intent= new Intent(PremierePage.this,MenuPlanche.class);
                                                        finish();
                                                        startActivity(intent);
                                                        break;

                                                }
                                            }
        });


        navButt.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v)
            {
                 dr.openMenu(true);
            }
        });


        dr.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        dr.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {



            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");

                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });
    }


        /*mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id= menuItem.getItemId();
                        menuItem.setChecked(true);
                        switch (id) {

                            case android.R.id.home:

                                return true;

                            case R.id.navigation_sub_item_1:
                                Intent intent= new Intent(PremierePage.this,MenuPlanche.class);
                                finish();
                                startActivity(intent);
                                break;
                            case R.id.navigation_sub_item_2:
                                intent= new Intent(PremierePage.this,NouveauStagiaireMenu.class);
                                finish();
                                startActivity(intent);
                                break;
                            case R.id.inscription:
                                String url = "http://www.cntregastel.com/je-minscris2.html";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                                break;
                            case R.id.tarifs:
                                String url2 = "http://www.cntregastel.com/_media/flyer-club-nautique-3-plis.pdf";
                                Intent i2 = new Intent(Intent.ACTION_VIEW);
                                i2.setData(Uri.parse(url2));
                                startActivity(i2);
                                break;
                            case R.id.nav_about:
                                intent= new Intent(PremierePage.this,About.class);
                                startActivity(intent);
                                break;
                        }

                        return true;
                    }
                });*/

    //}

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

   @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b) {
            youTubePlayer.cueVideo("aJWRrwkBYBk"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(PremierePage.this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();

    }

    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(),fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }




}
