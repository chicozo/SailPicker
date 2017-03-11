package doran.sailpicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doran on 04/02/2017.
 */
public class WBTraitement {
    //vent
    private static Pattern pattern1;
    private static Matcher matcher1;
    //direction
    private static Pattern pattern2;
    private static Matcher matcher2;
    int WBwindspeed;
    String WBDirec;
    public WBTraitement(){
        WBDirec="";
        WBwindspeed=0;
    }
    public void traitementWB(String s)
    {
        String data=s;
        /*pattern1=Pattern.compile("Vitesse du vent .*\n" +
                ".*\n" +
                ".*>(.*)<");
        matcher1 = pattern1.matcher(data);
        if(matcher1.find())
            this.WBwindspeed=Integer.parseInt(matcher1.group(1));*/
        pattern2=Pattern.compile(
                "Direction du vent(.*)?>"
                );
        matcher2 = pattern2.matcher(s);
        if(matcher2.find())
            this.WBDirec=matcher2.group(1);
    }

    public String getWBDirec() {
        return WBDirec;
    }

    public int getWBwindspeed() {
        return WBwindspeed;
    }
    public String toString()
    {
        return "Vent Anémo: "+this.WBwindspeed+" Direc Anémo: "+this.WBDirec;
    }
}
