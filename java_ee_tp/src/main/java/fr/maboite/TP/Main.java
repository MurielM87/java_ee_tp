package fr.maboite.TP;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*TP : lecture CSV
Le programme doit démarrer en lisant un fichier CSV qui contient une liste de mots
et les définitions associées
Via la console, il est possible de faire trois actions  (en tapant 1, 2, ou 3, par exemple) :
 - afficher la liste de mots, et leur définition, triée dans la console. Les résultats sont
 affichés dans l'ordre alphabétique croissant.
- Sauvegarder cette liste de mots dans le fichier sortie.csv. Si ce fichier existe déjà,
il est écrasé.
- En tapant un mot, afficher sa définition.
Il est demandé d'utiliser une implémentation de l'interface Map, et la bibliothèque OpenCSV .
La documentation pour lire un fichier CSV : https://opencsv.sourceforge.net/#reading_into_an_array_of_strings
La documentation pour écrire un fichier CSV : https://opencsv.sourceforge.net/#writing_from_an_array_of_strings
En cas d'erreur, afficher un message indiquant : "Erreur grave, le programme va s'arrêter", puis
afficher la pile d'appels de la méthode dans la console, et faire en sorte que le programme s'arrête.
 */
/*
Si le code de l'exemple pour le Writer ne fonctionne pas chez vous, essayez avec la ligne suivante :
```
ICSVWriter writer = new CSVWriterBuilder(new FileWriter("yourfile.csv"))
            .withSeparator(',')
            .build();
```
 */
public class Main {
    private static String fichierCSV = "entree.csv";
    private static final String fichierSortieCSV = "sortie.csv";
    private static Map<String, String> dictionnaire = new TreeMap<>();

    private static void lireCSV(String fichierCSV) {
        //Le programme doit démarrer en lisant un fichier CSV qui contient une liste de mots
        //et les définitions associées
        try (CSVReader reader = new CSVReader(new FileReader(Main.fichierCSV))) {
            String[] nextLine;
            // Ignorer la première ligne si elle contient des en-têtes
            //reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length >= 2) {
                    String mot = nextLine[0];
                    String definition = nextLine[1];
                    dictionnaire.put(mot, definition);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.println("Erreur grave, le programme va s'arrêter");
        System.exit(0);
    }

    private static void afficherMenu() {
        System.out.println("Choissisez une action : ");
        System.out.println("Tapez 1. Afficher la liste de mots et leur définition");
        System.out.println("Tapez 2. Sauvegarder cette liste de mots dans un fichier sortie.csv");
        System.out.println("Tapez 3. Afficher la définition d'un mot");
        System.out.println("Tapez 4. quitter");
        System.out.print("Votre choix : ");
    }

    //afficher liste
    private static void afficherListe() {
        for (Map.Entry<String, String> entry : dictionnaire.entrySet()) {
            System.out.println("Mot: " + entry.getKey() + " & Définition: " + entry.getValue());
        }
    }

    //enregistrer liste sous sortie.csv et, si déjà présent, écraser
    private static void enregistrerListe() throws IOException {
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("sortie.csv"))
                .withSeparator('\t')
                .build();
        // feed in your array (or convert your data to an array)
        String[] entries = dictionnaire.keySet().toArray(new String[0]);

//        for (TreeMap<String, String> data : dictionnaire) {
//            dictionnaire = Main.fichierCSV;
//            writer.writeNext(data.values().toArray(new String[0]));
//        }

        //fermer writer
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Erreur grave, le programme va s'arrêter");
        System.exit(0);

    }

    //trouver un mot et sa définition
    private static void afficherMotDefinition() {
        Scanner choixMot = new Scanner(System.in);
        System.out.print("choississez un mot : ");
        String motChoisi = choixMot.next().toLowerCase();
        String definition = dictionnaire.get(motChoisi).toLowerCase();

        if (definition != null) {
            System.out.println("Définition de " + motChoisi + " : " + definition);
        } else {
            System.out.println("Le mot " + motChoisi + " n'est pas trouvé dans le dictionnaire.");
        }

    }

    //quitter programme
    private static void quitterProgramme() {
        System.out.println("Au revoir");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        lireCSV(fichierCSV);
        Scanner keyboard = new Scanner(System.in);

        String choixNumero;
        while(true) {
            afficherMenu();
            choixNumero = keyboard.nextLine();
            switch (choixNumero) {
                case "1" -> afficherListe();
                case "2" -> enregistrerListe();
                case "3" -> afficherMotDefinition();
                case "4" -> quitterProgramme();
                default -> System.out.println("Choix invalide. Retentez votre chance.");
            }
        }
    }
}

