package jeu;

import gui.GUI;
import io.saves.SaveSignal;
import rules.*;
import rules.map.MapRule;

import java.util.ArrayList;
import java.util.List;

public class PartieMulti implements Partie{

    private final int nbJoueurs;
    private final int nbParties;
    private final MapRule rules;

    private final GUI gui;

    private final List<Integer> pointsPartie;

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

        pointsPartie = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            pointsPartie.add(0);
        }

        indexTourEnCours = 0;

    }

    private void updatePointsPartie(List<Integer> listePointsTour){
        for (int i = 0; i < nbJoueurs; i++) {
            pointsPartie.set(i, pointsPartie.get(i) + listePointsTour.get(i)); //addition point du tour avec ceux de la partie.
        }
    }

    private class TourPartieMulti{
        private final List<PartieSolo> parties;
        private final List<Boolean> partiesFinies;
        private final List<Integer> pointsTour;

        public List<PartieSolo> getParties() {
            return parties;
        }

        public List<Boolean> getPartiesFinies() {
            return partiesFinies;
        }

        private void setPointsTour(List<Integer> listeCptEssais){
            for (int i = 0; i < nbJoueurs; i++){
                //Pour chaque joueur, difference entre le nb d'essais max et le nb d'essais utilisés
                pointsTour.set(i,((Integer) rules.getValue("nbEssais")) - listeCptEssais.get(i));
            }
        }
        public List<Integer> getPointsTour() {
            return pointsTour;
        }

        TourPartieMulti(){
            parties = new ArrayList<>();
            partiesFinies = new ArrayList<>();
            pointsTour = new ArrayList<>();

            for (int i = 0; i < nbJoueurs; i++){
                partiesFinies.add(Boolean.FALSE);
                parties.add(new PartieSolo(gui, rules));
                pointsTour.add(0);
            }
        }

        private boolean tourFini(){
            for (Boolean b : partiesFinies){
                if (!b){
                    return false;
                }
            }
            return true;
        }
        public void launch() throws SaveSignal{
            List<Integer> listeCptEssais = new ArrayList<>(); //Compteur du nombre d'essais effectués par chaque joueur
            for (int i = 0; i < nbJoueurs; i++){
                listeCptEssais.add(0);
            }

            while (!tourFini()) { // Tant que toutes les parties solos qui composent le tour ne sont pas finies.

                for (int i = 0; i < nbJoueurs; i++) { // Pour chaque joueur
                    PartieSolo partieJoueur = parties.get(i);

                    if (!partiesFinies.get(i)) { //Si le joueur i n'a pas fini sa partie

                        listeCptEssais.set(i,listeCptEssais.get(i) + 1); //Incrémente compteur essai du joueur

                        gui.afficherInfosPartieMulti(indexTourEnCours, i+1); //Infos GUI

                        partieJoueur.doTour(); //Faire le tour (au sens tour de PartieSolo) du joueur.

                        if (partieJoueur.finie()) { // Si le tour a abouti à la fin de la PartieSolo du joueur
                            partiesFinies.set(i, Boolean.TRUE);
                            gui.afficherPlateau(partieJoueur.getPlateau());
                            gui.afficherMessageFinPartie(partieJoueur.gagnee(),i+1,listeCptEssais.get(i));
                            gui.getInputPause();
                        }
                    }
                }
            }
            //Compter les points des joueurs
            setPointsTour(listeCptEssais);
        }
    }

    @Override
    public void doTour() {
        // Tour de PartieMulti = Tour de PartieSolo * nbJoueurs
        TourPartieMulti tour = new TourPartieMulti();
        indexTourEnCours++;
        try {
            tour.launch(); //Lancement du tour
        } catch (SaveSignal e){
            //TODO Save état partie
        }
        //Mise à jour des points de la partie
        List<Integer> pointsTour = tour.getPointsTour();
        updatePointsPartie(pointsTour);

        //Affichage fin de tour (scores)
        gui.afficherScoresPartieMulti(pointsTour, pointsPartie);
        gui.getInputPause();
    }

    @Override
    public void launchPartie() {
        System.out.println(nbJoueurs);
        //Boucle de Partie
        while (indexTourEnCours <= nbParties) {
            doTour();
        }

        //Affichage Final de partie (podium)
        gui.afficherScoresPartieMulti(pointsPartie);
        gui.getInputPause();

    }
}
