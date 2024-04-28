package jeu;

import entities.Couleur;
import entities.Pion;
import gui.GUI;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import listes.RulesList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartieSolo implements Partie {
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
    public PartieSolo(RulesList rules, GUI gui) {

        int nbEssais = (int) rules.getElement(0).getValue();
        int tailleMaxCombinaison = (int) rules.getElement(1).getValue();
        int nbCouleur = (int) rules.getElement(2).getValue();
        boolean plsFoisMemeCouleur = (boolean) rules.getElement(3).getValue();


        //Selection aléatoire des couleurs
        couleurAutorisees = selecRandCouleurs(nbCouleur);

        //Création combinaison secrete aléatoire
        CombinaisonSecrete csRandom = genRandCombinaison(plsFoisMemeCouleur,tailleMaxCombinaison,nbCouleur);

        //Initialisation du plateau
        plateau = new Plateau(csRandom,nbEssais,tailleMaxCombinaison);

        //Initialisation de la gui
        this.gui = gui;
    }

    private void doTour(){
        //Affichage du plateau
        gui.afficherPlateau(plateau);

        //Création de la combinaison de l'essai du tour
        Combinaison essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison(), couleurAutorisees);
        while (essai == null){
            essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison(), couleurAutorisees);
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
