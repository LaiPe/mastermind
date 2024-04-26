package gui;

import jeu.Plateau;
import listes.Combinaison;

public interface GUI {
    public void afficherPlateau(Plateau plateau);
    Combinaison choixCombinaison(int tailleMax);
}
