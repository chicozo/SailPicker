package doran.sailpicker.analyse;

import doran.sailpicker.meteo.MeteoConverter;

/**
 * Created by doran on 24/01/2017.
 */
public class Decision {
    int vent,rafales,direction,niveau,poids,age,taille;
    int confiance;
    public Decision(int v,int r,int d,int n,int p,int a,int t)
    {
        this.vent=v;
        this.rafales=r;
        this.niveau=n;
        this.poids=p;
        this.age=a;
        this.taille=t;
        this.direction=d;
    }

    public String choixPlanche()
    {
        int val=100;
        String v,r,d;
        if(this.vent<14)
        {
            v="faible";
        }
        else if(this.vent<18&&this.vent>=14)
            v="moyen";
        else
            v="fort";
        if(this.rafales-this.vent<5)
            r="faible";
        else
            r="fort";
        MeteoConverter m=new MeteoConverter(this.vent,this.rafales,this.direction);
        d=m.direcToString();
        double imc=this.calculIMC();
        //instructions de remplissqage de l'arbre

        //on remplit la deuxieme valeure a null quand il n'y a plus de valeures a comparer
        /*for(int i=0;i<val;i++)
        {
        arbre[i][1]=null;
        }

        int j=0;
        while(arbre[j][1]!=null) {
            if (arbre[j][0].getClass().getName().equals("int")) {
                if ((int)arbre[j][1] <= (int)arbre[j][0]) {
                    j = 2*j + 1;
                } else {
                    j = 2*j + 2;
                }
            }
            if (arbre[j][0].getClass().getName().equals("String"))
            {
                if (arbre[j][1].equals(arbre[j][0])) {
                    j = 2*j + 1;
                } else {
                    j = 2*j + 2;
                }
            }
        }
        this.confiance=(int)arbre[j][2];
        return (String)arbre[j][0];*/

        if(imc <= 22) {
            if(niveau <= 1){
                this.confiance=(int)(76/77)*100;
                return "M";}
                    else {
                    if(v.equals("faible"))
                    {
                        this.confiance=100;
                        return "M";
                    }
                    else if(v.equals("fort")) {
                        if(age <= 14)
                        {
                            this.confiance=100;
                            return "M";
                        }
                                else {
                                    if(niveau <= 2) {
                                        if(age <= 22) {
                                                if(imc <= 21){
                                                    this.confiance=9/11*100;
                                                    return "S";

                                                }
                                            else{
                                                    this.confiance=100;
                                                    return "M";
                                                }
                                        }
                                                else{this.confiance = 90;}
                                    }
                                    else{
                                        this.confiance=(int)27/28*100;
                                        return "S";
                                    }
                        }
                    }
                    else {
                        if(age <= 29)
                        {
                            this.confiance=42/52*100;
                            return "M";
                        }
                        else{
                            this.confiance=100;
                            return "S";
                        }
                    }
                     }
    }
        else{
                if(age <= 18)
                {
                    this.confiance=76/84*100;
                    return "M";
                }
                       else {
                            if(imc <= 23) {
                                if(v.equals("faible"))
                                {
                                    if(d.equals("EST"))
                                    {this.confiance=2/3*100;
                                    return "L";}
                                    else{this.confiance = 6/7*100;
                                    return "M";}
                                }
                                else if(v.equals("fort")) {
                                    if(niveau <= 1) {
                                        this.confiance=100;
                                        return "L";
                                    }
                                    else {
                                        if(r.equals("faible")){
                                            this.confiance = 100;
                                            return "M";
                                        }
                                        else{
                                            this.confiance = 2/3*100;
                                            return "S";}
                                    }
                                }
                                else{this.confiance=4/5*100;
                                return "M";}
                            }
                            else {
                                if(niveau <= 2){
                                    this.confiance=23/25*100;
                                    return "L";
                                }
                                else {
                                    if(v.equals("faible")){
                                        this.confiance=6/7*100;
                                        return "L";
                                    }
                                    else if(v.equals("fort")){
                                        this.confiance=2/3*100;
                                        return "M";
                                    }
                                     else{
                                        this.confiance = 100;
                                        return "M";
                                    }
                                }
                            }
                }
            }
        return "";
    }
    public String choixVoile()
    {
        double imc=this.calculIMC();
        return "planche2";
    }
    public String conseil()
    {
        return "conseil";
    }
    public int confianceRate() {

        return this.confiance;
    }
    public double calculIMC()
    {
        return this.poids/(Math.pow(this.taille,2.0));
    }
}
