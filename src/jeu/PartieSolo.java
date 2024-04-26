package jeu;

import entities.Couleur;
import entities.Pion;
import gui.GUI;
import listes.Combinaison;
import listes.CombinaisonSecrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartieSolo {
    private final Plateau plateau;

    List<Couleur> couleurAutorisees;

    GUI gui;

    private List<Couleur> selecRandCouleurs(int nbCouleur){
        List<Couleur> couleurs = new ArrayList<>(8);
        for (int i = 1; i < 9; i++){
            couleurs.add(Couleur.getByIndex(i));
        }

        List<Couleur> randCouleurs = new ArrayList<>(nbCouleur);
        int bound = 8;
        for (int i = 0; i < nbCouleur; i++){
            int r = new Random().nextInt(bound);
            randCouleurs.add(couleurs.get(r));
            couleurs.remove(r);
            bound--;
        }
        return randCouleurs;
    }
    private CombinaisonSecrete genRandCombinaison(boolean plsFoisMemeCouleur, int tailleMax, int nbCouleur){
        List<Couleur> cAutorisees = new ArrayList<>(couleurAutorisees);

        CombinaisonSecrete csRandom = new CombinaisonSecrete(tailleMax);

        if (plsFoisMemeCouleur) {
            for (int i = 0; i < tailleMax; i++) {
                csRandom.addElement(new Pion(cAutorisees.get(new Random().nextInt(nbCouleur))));
            }
        }
        else {
            for (int i = 0; i < tailleMax; i++) {
                int r = new Random().nextInt(nbCouleur);
                Pion p = new Pion(cAutorisees.get(r));
                csRandom.addElement(p);
                cAutorisees.remove(r);
                nbCouleur--;
            }
        }
        return csRandom;
    }
    public PartieSolo(int nbEssaisMax, int tailleMaxCombinaison, int nbCouleur, boolean plsFoisMemeCouleur, GUI gui) throws IllegalArgumentException{

        //Vérification des arguments
        if ( nbCouleur < 6 || nbCouleur > 8){
            throw new IllegalArgumentException("Le nombre de couleur doit être compris entre 6 et 8.");
        }
        if (tailleMaxCombinaison < 4 || tailleMaxCombinaison > 5) {
            throw new IllegalArgumentException("Le nombre de pions doit être de 4 ou 5.");
        }
        if (!(nbEssaisMax == 10 || nbEssaisMax == 12)) {
            throw new IllegalArgumentException("Le nombre d'essais doit être de 10 ou 12.");
        }

        //Selection aléatoire des couleurs
        couleurAutorisees = selecRandCouleurs(nbCouleur);

        //Création combinaison secrete aléatoire
        CombinaisonSecrete csRandom = genRandCombinaison(plsFoisMemeCouleur,tailleMaxCombinaison,nbCouleur);

        //Initialisation du plateau
        plateau = new Plateau(csRandom,nbEssaisMax,tailleMaxCombinaison);

        //Initialisation de la gui
        this.gui = gui;
    }

    private void doTour(){
        //Affichage du plateau
        gui.afficherPlateau(plateau);

        //Création de la combinaison de l'essai du tour
        Combinaison essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison());
        while (essai == null){
            essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison());
        }

        //Placement de l'essai dans le plateau
        plateau.addEssai(essai);
    }

    public boolean launchPartie(){
        //Boucle de jeu
        while (!(plateau.estPlein() || plateau.getCombinaisonSecrete().decouverte())){
            doTour();
        }

        if (plateau.getCombinaisonSecrete().decouverte()){
            System.out.println("Bravo, vous avez gagné !");
            //gui affichage victoire
            return true;
        } else {
            System.out.println("Dommage, vous avez perdu...");
            //gui affichage défaite
            return false;
        }
    }
}
