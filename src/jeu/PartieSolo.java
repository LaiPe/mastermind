package jeu;

import entities.Couleur;
import entities.Pion;
import gui.GUI;
import io.saves.Save;
import io.saves.SaveSignal;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import rules.*;
import rules.map.MapRule;


import java.io.IOException;
import java.util.*;

public class PartieSolo implements Partie {
    private Plateau plateau;
    private List<Couleur> couleurAutorisees;

    private int indexTourEnCours;

    private GUI gui;

    private MapRule rules;

    public Plateau getPlateau() {
        return plateau;
    }
    public List<Couleur> getCouleurAutorisees() {
        return couleurAutorisees;
    }

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

        this.rules = rules;

        indexTourEnCours = 0;

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

    public boolean finie(){
        return (plateau.estPlein() || plateau.getCombinaisonSecrete().decouverte());
    }
    public boolean gagnee(){
        return plateau.getCombinaisonSecrete().decouverte();
    }

    @Override
    public void doTour() throws SaveSignal{
        boolean resValid = false;
        String messageErr = null;

        Combinaison essai = null;
        while (!resValid) {
            indexTourEnCours++; //TODO ATTENTION A METTRE APRES LE TRY CATCH
            gui.afficherPlateau(plateau); //Affichage du plateau
            try {
                essai = gui.choixCombinaison(plateau.getTailleMaxCombinaison(), couleurAutorisees, messageErr); //Création de la combinaison de l'essai du tour.
                resValid = true;
            } catch (IllegalArgumentException e){
                messageErr = e.getMessage();
            }
        }

        //Placement de l'essai dans le plateau
        plateau.addEssai(essai);
    }

    @Override
    public void launchPartie(){
        //Boucle de jeu
        while (!finie()){
            try {
                doTour();
            } catch (SaveSignal s){
                Save save = new Save(this.getClass().getSimpleName(),indexTourEnCours, rules);
                save.addDataJoueur(plateau,couleurAutorisees);
                try {
                    save.doSave();
                    System.out.println("Sauvegarde situé dans le répertoire \"" + save.getSavePathName() + "\".");
                } catch (IOException e){
                    gui.afficherErreur("Erreur lors de la création de la sauvegarde : " + e.getMessage());
                }

                gui.getInputPause();
                return;
            }
        }

        //Affichage Final
        gui.afficherPlateau(plateau);
        gui.afficherMessageFinPartie(gagnee(),indexTourEnCours);
        gui.getInputPause();
    }
}
