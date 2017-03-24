package doran.sailpicker.meteo;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doran on 29/01/2017.
 */
public class WindguruTraitement {
    //date actuelle
    Date d = new Date();
    //declaration des chaines utilisées pour recuperer les tableaux
    String chaine="";
    String chaine2="";
    String chaine3="";
    String chaine4="";
    String chaine5="";
    String[] separated;
    //declaration des pattern servant au traitement de la page web de windguru
    //choix du modèle GFS 27km
    private static Pattern pattern1;
    private static Matcher matcher1;
    //vitesse du vent
    private static Pattern pattern2;
    private static Matcher matcher2;
    //direction du vent
    private static Pattern pattern3;
    private static Matcher matcher3;
    //vitesse des rafales
    private static Pattern pattern4;
    private static Matcher matcher4;
    //horaire
    private static Pattern pattern5;
    private static Matcher matcher5;
    //jour
    private static Pattern pattern6;
    private static Matcher matcher6;
    int[] windspeed,rafales,direc,hr,day;

    public WindguruTraitement()
    {
       this.windspeed=null;
        this.rafales=null;
        this.direc=null;
        this.hr=null;
        this.day=null;
    }
    public void traitement(String data)
    {
        //recupération dujour et de l'heure actuelle
        SimpleDateFormat f1 = new SimpleDateFormat("HH");
        String s1 = f1.format(d);
        SimpleDateFormat f2 = new SimpleDateFormat("dd");
        String s2 = f2.format(d);
        int actuelHR= Integer.parseInt(s1);
        int actuelD=Integer.parseInt(s2);
        //debut de la recherche desdifférents tableaux
        pattern1 = Pattern.compile("\"model\":\"gfs\"(.*)\"model_name\":\"GFS 27 km");
        chaine=data;
        matcher1 = pattern1.matcher(chaine);
        if (matcher1.find()) {
            chaine5=chaine4=chaine3 = chaine2 = chaine = matcher1.group(1);
            pattern2 = Pattern.compile("\"WINDSPD\":(.*?)]");
            matcher2 = pattern2.matcher(chaine);

            if (matcher2.find()) {
                chaine = matcher2.group(1);
                chaine = chaine.replace("[", "");
                separated = chaine.split(",");
                this.windspeed = stringToInt(separated);
            }
            pattern3 = Pattern.compile("\"WINDDIR\":(.*?)]");
            matcher3 = pattern3.matcher(chaine2);
            if (matcher3.find()) {
                chaine2 = matcher3.group(1);
                chaine2 = chaine2.replace("[", "");
                separated = chaine2.split(",");
                this.direc = stringToInt(separated);
            }
            pattern4 = Pattern.compile("\"GUST\":(.*?)]");
            matcher4 = pattern4.matcher(chaine3);
            if (matcher4.find()) {
                chaine3 = matcher4.group(1);
                chaine3 = chaine3.replace("[","");
                separated = chaine3.split(",");
                this.rafales = stringToInt(separated);
            }
            pattern5 = Pattern.compile("\"hr_h\":(.*?)]");
            matcher5 = pattern5.matcher(chaine4);
            if (matcher5.find()) {
                chaine4 = matcher5.group(1);
                chaine4 = chaine4.replace("[","");
                chaine4=chaine4.replace("\"","");
                separated = chaine4.split(",");
                this.hr = stringToInt(separated);
            }
            pattern6 = Pattern.compile("\"hr_d\":(.*?)]");
            matcher6 = pattern6.matcher(chaine5);
            if (matcher6.find()) {
                chaine5 = matcher6.group(1);
                chaine5 = chaine5.replace("[","");
                chaine5=chaine5.replace("\"","");
                separated = chaine5.split(",");
                this.day = stringToInt(separated);
            }

            int i=heureLaPlusProche(actuelHR,actuelD,this.hr,this.day);
            this.reductionTab(i);

        }
    }

    public int[] tronconne(int[] tab,int i)
    {
        int[] tab2=new int[tab.length-i];
        int j=0;
        while(j<tab2.length)
        {
            tab2[j]=tab[i];
            j++;
            i++;
        }
        return tab2;
    }
    public void reductionTab(int i)
    {
        this.windspeed=tronconne(this.windspeed,i);
        this.rafales=tronconne(this.rafales,i);
        this.direc=tronconne(this.direc,i);
    }
    public int heureLaPlusProche(int h,int d,int[] hor,int[] jour)
    {
        int i=0;
        int pos=0;
        int delta=100;
        while(d!=jour[i])
            i++;
        while(d==jour[i])
        {
            if(Math.abs(h-hor[i])<delta)
            {
                delta=Math.abs(h-hor[i]);
                pos=i;
            }
            i++;
        }
        return pos;
    }
    public int[] getWindspeed() {
        return windspeed;
    }

    public int[] getRafales() {
        return rafales;
    }

    public int[] getDirec() {
        return direc;
    }

    public int[] getHr() {
        return hr;
    }

    public int[] getDay() {
        return day;
    }

    public int[] stringToInt (final String[] v)
    {
        int i = 0;
        int i2[] = new int[v.length];
        int echange;
        while (i < v.length - 1) {


            try {
                echange = (int) Double.parseDouble(v[i]);
                i2[i] = echange;
            } catch (NumberFormatException e) {
                i2[i] = 0;
            }


            i++;
        }
        return i2;
    }
    public String toString()
    {
        return this.windspeed[0]+""+this.rafales[0]+""+this.direc[0];
    }
}
