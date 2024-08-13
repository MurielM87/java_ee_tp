package fr.maboite.TP.clubFoot;


import java.util.Comparator;

/*
- créer une classe ClubDeFoot qui contient :
   * un nom (String)
   * un nombre de matchs remportés (Integer)
   * un nombre de matchs nuls (Integer)
   * un nombre de matchs perdus (Integer)
 */
/*
    - Bonus : ajouter un attribut nombre de buts marqués (Integer). En cas d'égalité du score de
    la règle précédente, c'est le club avec le plus haut nombre de buts qui est affiché en premier.
*/
public class ClubFoot {
    private String nomClub;
    private int matchsRemportes;
    private int matchsNuls;
    private int matchsPerdus;
    private int butsMarques;

    public ClubFoot(String nomClub, int matchsRemportes, int matchsNuls, int matchsPerdus, int butsMarques) {
        this.nomClub = nomClub;
        this.matchsRemportes = matchsRemportes;
        this.matchsNuls = matchsNuls;
        this.matchsPerdus = matchsPerdus;
        this.butsMarques = butsMarques;
    }

    public String getNomClub() {
        return nomClub;
    }
    public int getMatchsRemportes() {
        return matchsRemportes;
    }
    public int getMatchsPerdus() {
        return matchsPerdus;
    }
    public int getMatchsNuls() {
        return matchsNuls;
    }
    public int getButsMarques() {
        return butsMarques;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }
    public void setMatchsRemportes(int matchsRemportes) {
        this.matchsRemportes = matchsRemportes;
    }
    public void setMatchsNuls(int matchsNuls) {
        this.matchsNuls = matchsNuls;
    }
    public void setMatchsPerdus(int matchsPerdus) {
        this.matchsPerdus = matchsPerdus;
    }
    public void setButsMarques(int butsMarques) {
        this.butsMarques = butsMarques;
    }

    //le score vaut : "nombre de matchs remportés * 3 + nombre de matchs nuls".
    // Le club avec le plus haut score est affiché en premier.
    public int calculerScore() {
       return (this.matchsRemportes * 3) + (this.matchsNuls);
    }

    public static class ComparaisonScoresDecroissant implements Comparator<ClubFoot> {
        public int compare(ClubFoot cf1, ClubFoot cf2) {
            int score1 = cf1.calculerScore();
            int score2 = cf2.calculerScore();
            if (score1 != score2) {
                return score2 - score1;
            }
            return score1;
        }
    }

    public static class ComparaisonButsMarques implements Comparator<ClubFoot> {
        @Override
        public int compare(ClubFoot buts1, ClubFoot buts2) {
            return Integer.compare(buts2.getButsMarques(), buts1.getButsMarques());
        }
    }

}
