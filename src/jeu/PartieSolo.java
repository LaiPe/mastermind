package jeu;

import entities.Couleur;
import entities.Pion;
import gui.GUI;
import gui.TerminalGUI;
import io.SaveSignal;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import rules.*;
import rules.map.MapRule;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PartieSolo implements Partie<Boolean> {
    private Plateau plateau;

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
    private void initPartieSolo(GUI gui, MapRule rules){

        int nbEssais = (int) rules.getValue("nbEssais");
        int tailleMaxCombinaison = (int) rules.getValue("tailleMaxCombinaison");
        int nbCouleur = (int) rules.getValue("nbCouleur");
        boolean plsFoisMemeCouleur = (boolean) rules.getValue("plsFoisMemeCouleur");


        //Selection aléatoire des couleurs
        couleurAutorisees = selecRandCouleurs(nbCouleur);

        //Création combinaison secrete aléatoire
        CombinaisonSecrete csRandom = genRandCombinaison(plsFoisMemeCouleur,tailleMaxCombinaison,nbCouleur);

        //Initialisation du plateau
        plateau = new Plateau(csRandom,nbEssais,tailleMaxCombinaison);

        //Initialisation de la gui
        this.gui = gui;

    }
    public PartieSolo(GUI gui, MapRule rules) {
        initPartieSolo(gui, rules);
    }
    public PartieSolo(GUI gui) {

        MapRule rules = new MapRule();

        rules.set("nbEssais", new NbEssais());
        rules.set("tailleMaxCombinaison", new TailleMaxCombinaison());
        rules.set("nbCouleur", new NbCouleur());
        rules.set("plsFoisMemeCouleur", new PlsFoisMemeCouleur());
        rules.set("affichageTexte", new AffichageTexte());

        gui.choixRules(rules);

        gui.setAffichageTexte((boolean) rules.getValue("affichageTexte"));

        initPartieSolo(gui, rules);
    }

    @Override
    public Boolean doTour() {
        boolean resValid = false;
        String messageErr = null;

        Combinaison essai = null;
        while (!resValid) {

            gui.afficherPlateau(plateau); //Affichage du plateau
            try {
                essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison(), couleurAutorisees, messageErr); //Création de la combinaison de l'essai du tour.
                resValid = true;
            } catch (IllegalArgumentException e){
                messageErr = e.getMessage();
            } catch (SaveSignal e) {
                //TODO Point départ sauvegarde
            }
        }

        //Placement de l'essai dans le plateau
        plateau.addEssai(essai);

        return plateau.getCombinaisonSecrete().decouverte();
    }

    @Override
    public boolean launchPartie(){
        boolean resultTour = false;
        //Boucle de jeu
        while (!(plateau.estPlein() || resultTour)){
            resultTour = doTour();
        }

        gui.afficherPlateau(plateau);
        boolean resultPartie = false;
        if (resultTour){
            System.out.println("Bravo, vous avez gagné !");
            //gui affichage victoire
            resultPartie = true;
        } else {
            System.out.println("Dommage, vous avez perdu...");
            //gui affichage défaite
            resultPartie = false;
        }
        gui.getInputPause();

        return resultPartie;
        
    }
}
