package doran.sailpicker.Widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
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
public class AppWidget extends AppWidgetProvider {




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int length = appWidgetIds.length;
        for (int i = 0 ; i < length ; i++) {
            Log.i("boucle", "je rentre");

            // On récupère le RemoteViews qui correspond à l'AppWidget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            Log.i("boucle", "j'ai affiché bonjour 1");

            // Et il faut mettre à jour toutes les vues
            new GetHtmlCode(views, appWidgetManager,appWidgetIds).execute("https://www.windguru.cz/48425");
            Log.i("boucle", "j'ai executé");

            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);
    }

}

