package jeu;

import gui.GUI;
import io.save.SaveSignal;
import rules.*;
import rules.map.MapRule;

import java.util.ArrayList;
import java.util.List;

public class PartieMulti implements Partie<List<Integer>>{

    private int nbJoueurs;
    private int nbParties;
    private MapRule rules;

    private GUI gui;

    private int indexTourEnCours;

    public PartieMulti(GUI gui){

        MapRule rules = new MapRule();

        rules.set("nbJoueurs", new NbJoueurs());
        rules.set("nbParties", new NbParties());

        rules.set("nbEssais", new NbEssais());
        rules.set("tailleMaxCombinaison", new TailleMaxCombinaison());
        rules.set("nbCouleur", new NbCouleur());
        rules.set("plsFoisMemeCouleur", new PlsFoisMemeCouleur());
        rules.set("affichageTexte", new AffichageTexte());

        gui.choixRules(rules);

        gui.setAffichageTexte((boolean) rules.getValue("affichageTexte"));
        nbJoueurs = (int) rules.getValue("nbJoueurs");
        nbParties = (int) rules.getValue("nbParties");

        this.rules = rules;
        this.gui = gui;

        indexTourEnCours = 1;

    }

    @Override
    public List<Integer> doTour() throws SaveSignal {
        List<PartieSolo> listeParties = new ArrayList<>();
        List<Boolean> listePartiesFinies = new ArrayList<>();
        List<Integer> listeCptEssais = new ArrayList<>();

        for (int i = 0; i < nbJoueurs; i++){
            listePartiesFinies.add(Boolean.FALSE);
            listeParties.add(new PartieSolo(gui, rules));
            listeCptEssais.add(0);
        }

        //Boucle du Tour
        while (!tourFini(listePartiesFinies)) { // Tant que toutes les parties solos qui composent le tour ne sont pas finies.
            for (int i = 0; i < nbJoueurs; i++) {
                if (!listePartiesFinies.get(i)) { //Si le joueur i n'a pas fini sa partie
                    listeCptEssais.set(i,listeCptEssais.get(i) + 1); //Incrémente compteur essai du joueur

                    gui.afficherInfosPartieMulti(indexTourEnCours, i+1);
                    if (listeParties.get(i).doTour()) {
                        listePartiesFinies.set(i, Boolean.TRUE);
                        gui.afficherVictoirePartieMulti(i+1,listeCptEssais.get(i));
                        gui.getInputPause();
                    }
                }
            }
        }
        indexTourEnCours++;

        List<Integer> listePoints = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++){
            listePoints.add(((Integer) rules.getValue("nbEssais")) - listeCptEssais.get(i));
            //Pour chaque joueur, difference entre le nb d'essais max et le nb d'essais utilisés
        }
        return listePoints;
    }

    private boolean tourFini(List<Boolean> listePartiesFinies){
        for (Boolean b : listePartiesFinies){
            if (!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean launchPartie() {
        List<Integer> listePointsPartie = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            listePointsPartie.add(0);
        }

        //Boucle de Partie
        List<Integer> listePointsTour = null;

        while (indexTourEnCours <= nbParties) {
            listePointsTour = doTour();

            for (int i = 0; i < nbJoueurs; i++) {
                listePointsPartie.set(i, listePointsPartie.get(i) + listePointsTour.get(i)); //addition point du tour avec ceux de la partie.
            }

            gui.afficherScoresPartieMulti(false, listePointsTour, listePointsPartie);
            gui.getInputPause();

        }

        gui.afficherScoresPartieMulti(true, listePointsTour, listePointsPartie);
        gui.getInputPause();
        return true;
    }
}
