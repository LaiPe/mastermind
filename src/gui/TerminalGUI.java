package gui;

import jeu.Plateau;

import java.io.IOException;

public class TerminalGUI {
    private boolean affichageTexte;

    public TerminalGUI(boolean affichageTexte){
        this.affichageTexte = affichageTexte;
    }

    public void afficherPlateau(Plateau plateau){
        System.out.println("\033[H\033[2J");

        System.out.println("======== Combinaison Secrète =========");
        System.out.println(plateau.getCombinaisonSecrete());

        System.out.println("=============== Essais ===============");
        for (int i = 0; i < plateau.getNbEssaisMax(); i++) {
            if (plateau.getResultEssai(i).getTaille() > 0 && affichageTexte) {
                System.out.println(plateau.getCombinaisonEssai(i));
                System.out.println("==> " + plateau.getResultEssai(i).description());
            }
            else if (plateau.getResultEssai(i).getTaille() > 0) {
                System.out.println(plateau.getCombinaisonEssai(i) + " ==> " + plateau.getResultEssai(i));
            }
            else {
                System.out.println(plateau.getCombinaisonEssai(i));
            }
        }

        System.out.println();
    }
}
