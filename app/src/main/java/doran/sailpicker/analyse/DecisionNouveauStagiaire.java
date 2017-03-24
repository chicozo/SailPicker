package doran.sailpicker.analyse;

/**
 * Created by doran on 28/02/2017.
 */
public class DecisionNouveauStagiaire {
    int a,n,p,s;
    String vidUrl;
    public DecisionNouveauStagiaire(int age,int niveau,int poids,int support){
        this.a=age;
        this.n=niveau;
        this.p=poids;
        this.s=support;
        this. vidUrl="";
    }
    public String resolution()
    {
        if(this.a==5&&this.p==1)
            this.a=4;
        if (this.a==7)
            this.a=6;
        String res="";
        if (this.s==1 || this.s==3)
        {
            switch (this.a){
                case 1:
                    res+="Deriveur:\n Tu peux commencer en Jardin des mers où tu découvriras en douceur le milieu marin!";
                    break;
                case 2:
                    res+="Deriveur:\n Tu peux aller en Moussaillon";
                    break;
                case 3:
                    if(this.n==0)
                        res+="Deriveur:\n Tu peux aller en Mousse où tu découvriras tous les supports";
                    else if(this.n==1||this.n==2)
                        res+="Deriveur:\n Tu peux aller en Optimiste où tu perfectionneras ta navigation";
                    else
                        res+="Deriveur:\n Tu peux aller en Optimiste Solo";
                    break;
                case 4:
                    switch (this.n){
                        case 0:
                            res+="Deriveur:\n Tu peux aller en Topper à 2";
                            break;
                        case 1:
                            res+="Deriveur:\n Tu peux aller en Topper à 2";
                            break;
                        case 2:
                            res+="Deriveur:\n Tu peux aller en Topaz ou bien en Topper solo";
                            break;
                        case 3:
                            res+="Deriveur:\n Tu peux aller en Topaz ou bien en Topper solo";
                            break;
                        case 4:
                            res+="Deriveur:\n Tu peux aller en Topaz ou bien en Topper solo";
                            break;
                        case 5:
                            res+="Deriveur:\n Tu peux aller en Topaz ou bien en Topper solo";
                            break;
                    }
                    break;
                case 5:
                    switch (this.n){
                        case 0:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 1:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 2:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 3:
                            res+="Deriveur:\n Tu peux aller en Buzz ou en Laser";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                        case 4:
                            res+="Deriveur:\n Tu peux aller en Buzz ou en Laser.\n A tu penser à venir t'entrainer régulièrement au club? ";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                        case 5:
                            res+="Deriveur:\n Tu peux aller en Buzz ou en Laser.\n A tu penser à venir t'entrainer régulièrement au club?";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                    }
                    break;
                case 6:
                    switch (this.n){
                        case 0:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 1:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 2:
                            res+="Deriveur:\n Tu peux aller en 420 ou Argo";
                            this.vidUrl="_JdSBhNf_ss";
                            break;
                        case 3:
                            res+="Deriveur:\n Tu peux aller en Buzz en Laser ou bien en ISO.\n Tenté par une formation d'aide moniteur? Renseigne toi à l'accueil!";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                        case 4:
                            res+="Deriveur:\n Tu peux aller en Buzz en Laser ou bien en ISO.\n Tenté par une formation d'aide moniteur? Renseigne toi à l'accueil!";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                        case 5:
                            res+="Deriveur:\n Tu peux aller en Buzz en Laser ou bien en ISO.\n Tenté par une formation d'aide moniteur? Renseigne toi à l'accueil!";
                            this.vidUrl="hxiGxWe99RE";
                            break;
                    }
                    break;
            }

        }
        if (this.s==1 || this.s==2)
        {
            if (this.a<4)
                res+="\nPlanche:\nC'est encore un peu tôt pour toi!";
            else
            {
                res+="\nPlanche:\nTu peux t'inscrire en planche à voile!";
                this.vidUrl="RqVqo0NgxYw";
                if (this.n>3)
                {
                    res+="\nRenseigne toi tu as surement le niveau pour faire du foil!";

                }
            }
        }
        if (this.s==1 || this.s==4)
        {
            if (this.a<4)
                res+="\nKayak:\nC'est encore un peu tôt pour toi!";
            else
            {
                res+="\nKayak:\nTu peux t'inscrire en Kayak!";
                this.vidUrl="q796OArCrko";

            }
        }
        if (this.s==1)
            this.vidUrl="O5NOtL8jKuU&";

        return res;
    }
    public String getvidUrl()
    {
        return this.vidUrl;
    }
}
