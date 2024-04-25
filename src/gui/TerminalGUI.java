package gui;

import jeu.Plateau;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.IOException;

public class TerminalGUI {
    public static void afficherPlateau(Plateau plateau){
        System.out.println();

        System.out.println("======== Combinaison Secrète =========");
        System.out.println(plateau.getCombinaisonSecrete());
        System.out.println();

        System.out.println("=============== Essais ===============");
        for (int i = 0; i < plateau.getNbEssaisMax(); i++) {
            if (plateau.getResultEssai(i).getTaille() > 0) {
                System.out.println(plateau.getCombinaisonEssai(i) + " ==> " + plateau.getResultEssai(i));
            } else {
                System.out.println(plateau.getCombinaisonEssai(i));
            }
        }

        System.out.println();

    }
}
