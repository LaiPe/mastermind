package gui;

import jeu.Plateau;
import listes.Combinaison;

import java.io.IOException;

public class TerminalGUI implements GUI {
    private boolean affichageTexte;

    public TerminalGUI(boolean affichageTexte){
        this.affichageTexte = affichageTexte;
    }

    public void afficherPlateau(Plateau plateau){
        System.out.println("\033[H\033[2J");

        System.out.println("======== Combinaison Secrète =========");
        System.out.println(plateau.getCombinaisonSecrete());

        System.out.println("=============== Essais ===============");
        for (int i = 0; i < plateau.getNbEssais(); i++) {
            if (affichageTexte) {
                System.out.println(plateau.getCombinaisonEssai(i));
                System.out.println("==> " + plateau.getResultEssai(i).description());
            }
            else {
                System.out.println(plateau.getCombinaisonEssai(i) + " ==> " + plateau.getResultEssai(i));
            }

        }

        Combinaison factice = new Combinaison(plateau.getTailleMaxCombinaison());
        for (int i = plateau.getNbEssais(); i < plateau.getNbEssaisMax(); i++) {
            System.out.println(factice);
        }

        System.out.println();
    }
}
