package doran.sailpicker.Widget;

import android.appwidget.AppWidgetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import doran.sailpicker.R;
import doran.sailpicker.meteo.MeteoConverter;
import doran.sailpicker.meteo.WindguruTraitement;

/**
 * Created by doran on 25/03/2017.
 */
public class GetHtmlCode extends AsyncTask<String, Void, String> {
    String chaine;
    int[] windspeed;
    int[] winddirectory;
    int[] rafales;
    WindguruTraitement mt;
    MeteoConverter m;
    RemoteViews views;
    AppWidgetManager appWidgetManager;
    int[] appWidgetIds;
    public GetHtmlCode(RemoteViews v,AppWidgetManager apm, int [] appIds){
        this.views=v;
        this.appWidgetIds=appIds;
        this.appWidgetManager=apm;
        this.chaine="";
/*        for(int i =0;i<winddirectory.length;i++){
        this.winddirectory[i]=0;}
        for(int i =0;i<windspeed.length;i++){
            this.windspeed[i]=0;}
        for(int i =0;i<rafales.length;i++){
            this.rafales[i]=0;}*/

    }
    public int[] getWindspeed(){
        return windspeed;
    }
    public int[] getWinddirectory(){
        return winddirectory;
    }
    public int[] getRafales(){
        return rafales;
    }
    public MeteoConverter getM(){
        return m;
    }
    public String getChaine(){
        return chaine;
    }
    @Override
    protected String doInBackground(String... params) {
        Log.i("boucle", "async task");

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
        Log.i("boucle", codeHtml);

        return codeHtml;
    }

    @Override
    protected void onPostExecute(String result) {
        chaine=result;
        // result contient le code html
       Thread traitementMeteo = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mt = new WindguruTraitement();
                    Thread.sleep(500);
                    mt.traitement(chaine);
                    Thread.sleep(500);
                    windspeed = mt.getWindspeed();
                    winddirectory = mt.getDirec();
                    rafales = mt.getRafales();
                    m = new MeteoConverter(windspeed[0], rafales[0], winddirectory[0]);
                    if(windspeed!=null&&m!=null){
                        views.setTextViewText(R.id.link, "vent:"+windspeed[0]+"direc:"+m.direcToString()+"rafales:"+rafales[0]);}
                    else{
                        views.setTextViewText(R.id.link,"meteo");
                    }
                    appWidgetManager.updateAppWidget(appWidgetIds, views);
                    Log.i("boucle", "post execute");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        traitementMeteo.start();



    }

}