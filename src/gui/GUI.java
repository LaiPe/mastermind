package gui;

import entities.Couleur;
import jeu.Plateau;
import listes.Combinaison;
import rules.Rule;
import rules.map.MapRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface GUI {
    void afficherPlateau(Plateau plateau);

    Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees);

    void setAffichageTexte(boolean affichageTexte);

    MapRule choixRules(MapRule rules);

    static GUI choixGUI() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        GUI choix = new TerminalGUI(); // Valeur par défaut

        boolean validRes = false;

        while (!validRes) {
            System.out.println(Couleur.JAUNE + "Quel mode d'affichage souhaitez-vous ? : ");
            System.out.println("1. Affichage dans le terminal");
            System.out.println("2. Interface graphique" + Couleur.RESET);

            try {
                String res = reader.readLine();
                switch (res) {
                    case "1":
                        choix = new TerminalGUI();
                        validRes = true;
                        break;
                    case "2":
                        System.out.println("WIP : ce mode d'affichage n'est pas encore disponible, veuillez en choisir un autre !");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez choisir 1 ou 2.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return choix;
    }
}
