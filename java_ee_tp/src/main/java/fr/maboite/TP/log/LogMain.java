package fr.maboite.TP.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMain {

    //créer log
    private Logger log = LoggerFactory.getLogger(LogMain.class);

    public static void main(String[] args) {
        LogMain logMain = new LogMain();
        int resultat = logMain.additionner(3,5);
        System.out.println("résultat addition : " + resultat);

        resultat = logMain.calcule1(3,5);
        System.out.println("résultat calcul1 : " + resultat); //si b = 5, division par 0, alors error
    }

    private int calcule1(int a, int b) {
        log.info("je calcule avec a = " + a + " et b = " + b);
        return a / (5 - b);
    }

    private int additionner(int a, int b) {
        return a + b;
    }
}
