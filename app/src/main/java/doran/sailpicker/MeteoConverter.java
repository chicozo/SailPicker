package doran.sailpicker;

/**
 * Created by doran on 29/01/2017.
 */
public class MeteoConverter {
    int windspeed,rafales,direc;
    public MeteoConverter(int v,int r,int d)
    {
        this.windspeed=v;
        this.rafales=r;
        this.direc=d;
    }
    public String direcToString()
    {
        String res="";
        if (this.direc<45||this.direc>315)
        {
            res+=" NORD ";
        }
        if (this.direc>45&&this.direc<135)
            res+=" EST ";
        if(this.direc>135&&this.direc<225)
            res+=" SUD ";
        if(this.direc>225&&this.direc<315)
            res+=" WEST ";
        return res;
    }
    public  String toString()
    {
        return "vent moyen: "+this.windspeed+" rafales: "+this.rafales+" direction :"+this.direcToString();
    }
}
