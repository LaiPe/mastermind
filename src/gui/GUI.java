package gui;

import entities.Couleur;
import jeu.Plateau;
import listes.Combinaison;

import java.util.List;

public interface GUI {
    public void afficherPlateau(Plateau plateau);

    Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees);
}
