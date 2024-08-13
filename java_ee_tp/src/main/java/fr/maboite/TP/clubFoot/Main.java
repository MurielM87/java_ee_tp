package fr.maboite.TP.clubFoot;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, ClubFoot> classementClubFoot = new TreeMap<>();

        ClubFoot club1 = new ClubFoot("QuincyFoot", 2,2,2, 5);
        ClubFoot club2 = new ClubFoot("EpinayFoot", 3,1,2, 9);
        ClubFoot club3 = new ClubFoot("BoussyFoot", 1,3,2, 1);
        ClubFoot club4 = new ClubFoot("BrunoyFoot", 0,4,2, 3);
        ClubFoot club5 = new ClubFoot("MandresFoot", 5,1,0, 10);
        ClubFoot club6 = new ClubFoot("VarennesFoot", 1,4,1, 12);

        //ajouter les clubs
        classementClubFoot.put(club1.getNomClub(), club1);
        classementClubFoot.put(club2.getNomClub(), club2);
        classementClubFoot.put(club3.getNomClub(), club3);
        classementClubFoot.put(club4.getNomClub(), club4);
        classementClubFoot.put(club5.getNomClub(), club5);
        classementClubFoot.put(club6.getNomClub(), club6);

        //affichage des clubs avec le score
        for (Map.Entry<String, ClubFoot> entry : classementClubFoot.entrySet()) {
            ClubFoot club = entry.getValue();
            System.out.printf("Alphabétique : Club: %s, Score: %d \n", club.getNomClub(), club.calculerScore());
        }

        // Convertir TreeMap en une liste avec Map.Entry
        List<Map.Entry<String, ClubFoot>> clubList = new ArrayList<>(classementClubFoot.entrySet());
        clubList.sort((e1, e2) -> {
            ClubFoot clubTest1 = e1.getValue();
            ClubFoot clubTest2 = e2.getValue();
            return new ClubFoot.ComparaisonScoresDecroissant().compare(clubTest1, clubTest2);
        });
        // Afficher le classement des clubs en fonction de leur score
        int rank = 1;
        for (Map.Entry<String, ClubFoot> entry : clubList) {
            ClubFoot club = entry.getValue();
            System.out.printf("Classement : %d, Club : %s, Score : %d\n", rank, club.getNomClub(), club.calculerScore());
            rank++;
        }


        //afficher le classement des clubs en fonction des buts marqués (en ordre décroissant)
        // Convertir TreeMap en une liste avec Map.Entry pour trier par buts marqués
        List<ClubFoot> clubListButs = new ArrayList<>(classementClubFoot.values());
        clubListButs.sort(new ClubFoot.ComparaisonButsMarques());

        // Afficher le classement des clubs en fonction des buts marqués
        int rankButs = 1;
        for (ClubFoot club : clubListButs) {
            System.out.printf("Classement par buts : %d, Club : %s, Nombre de buts : %d, Score : %d \n", rankButs, club.getNomClub(), club.getButsMarques(), club.calculerScore());
            rankButs++;
        }

        /*
        Si vous voulez vous amuser, vous pouvez faire un test JUnit pour le ClubDeFoot,
        et lancer des IllegalArgumentException si quelqu'un essaie de mettre un nombre de matchs
        remportés négatif dans votre ClubDeFoot.
         */
    }
}
