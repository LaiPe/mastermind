package gui;

import entities.Couleur;
import entities.Pion;
import io.SaveSignal;
import jeu.Plateau;
import listes.Combinaison;
import rules.Rule;
import rules.map.MapRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TerminalGUI implements GUI {
    private boolean affichageTexte;
    public TerminalGUI(){}
    @Override
    public void setAffichageTexte(boolean affichageTexte) {
        this.affichageTexte = affichageTexte;
    }



    public String getInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e); //Plantage du programme
        }
    }
    @Override
    public void getInputPause() {
        System.out.println("(Veuillez appuyer sur la touche 'Entrée' pour continuer)");
        getInput();
    }
    private void clear(){
        System.out.println("\033[H\033[2J");
    }




    @Override
    public void afficherErreur(String message) {
        System.out.println(Couleur.ROUGE + message + Couleur.RESET);
    }
    @Override
    public void afficherPlateau(Plateau plateau){
        System.out.println("======== Combinaison Secrète =========");
        System.out.println(plateau.getCombinaisonSecrete());

        System.out.println("=============== Essais ===============");
        for (int i = 0; i < plateau.getNbEssais(); i++) {
            if (affichageTexte) {
                System.out.println(plateau.getCombinaisonEssai(i));
                System.out.println("==> " + plateau.getResultEssai(i).description());
            }
            else {
                System.out.println(plateau.getCombinaisonEssai(i) + " ==> " + plateau.getResultEssai(i));
            }

        }

        Combinaison factice = new Combinaison(plateau.getTailleMaxCombinaison());
        for (int i = plateau.getNbEssais(); i < plateau.getNbEssaisMax(); i++) {
            System.out.println(factice);
        }
    }
    @Override
    public void afficherInfosPartieMulti(int indexPartie, int indexJoueur) {
        System.out.println("======== Partie " +
                indexPartie + " ; " +
                Couleur.getByIndex((1 + indexJoueur)%8) + "Joueur " + indexJoueur + Couleur.RESET +
                " ========="
        );
    }
    @Override
    public void afficherVictoirePartieMulti(int indexJoueur, int cptEssais) {
        System.out.println(Couleur.VERT + "Félicitation Joueur " + indexJoueur + ", vous avez deviné votre combinaison secrète en " + cptEssais + " essais !" + Couleur.RESET);
    }
    @Override
    public void afficherScoresPartieMulti(boolean finPartie, List<Integer> listePointsTour, List<Integer> listePointsPartie) {
        if (finPartie){
            List<Integer> listePoints = new ArrayList<>(listePointsPartie);
            List<Integer> podium = new ArrayList<>();

            int taillePodium = Math.min(listePointsPartie.size(), 3);

            for (int i = 0; i < taillePodium; i++){
                int max = listePoints.get(0);
                int index = 0;

                for (int j = 1; j < listePoints.size(); j++) {
                    int elementCourant = listePoints.get(i);
                    if (listePoints.get(j) > max) {
                        max = elementCourant;
                        index = j;
                    }
                }
                listePoints.remove(index);
                podium.add(index);
            }

            System.out.println("============== Podium ================");
            System.out.println("1er : Joueur " + (podium.get(0)+1) + " avec " + listePointsPartie.get(podium.get(0)) + " points !");
            for (int i = 1; i < taillePodium; i++) {
                System.out.println((i+1) + "ème : Joueur " + (podium.get(i)+1) + " avec " + listePointsPartie.get(podium.get(i)) + " points !");
            }

        } else {
            System.out.println("========= Tableau des scores =========");
            System.out.println("Ce tour :");
            for (int i = 0; i < listePointsTour.size(); i++) {
                System.out.println("- Joueur " + (i+1) + " : +" + listePointsTour.get(i) + " points");
            }
            System.out.println("Récapitulatif des scores totaux :");
            for (int i = 0; i < listePointsTour.size(); i++) {
                System.out.println("- Joueur " + (i+1) + " : " + listePointsPartie.get(i) + " points");
            }
        }
    }





    @Override
    public MapRule choixRules(MapRule rules){
        clear();
        System.out.println("============ Paramètres ==============");
        for(Rule rule : rules.allRules()){
            boolean validRes = false;
            while (!validRes) {
                System.out.print(rule.getDemande() + " : ");
                try {
                    String res = getInput();
                    rule.setValue(res);
                    validRes = true;
                } catch (IllegalArgumentException e){
                    afficherErreur(e.getMessage());
                }
            }
        }
        clear();
        return rules;
    }



    private Combinaison parse(String response, int tailleMax, List<Couleur> couleursAutorisees) throws IllegalArgumentException{
        if (response.length() > tailleMax){
            throw new IllegalArgumentException("La combinaison rentrée est trop longue !");
        } else if (response.length() < tailleMax){
            throw new IllegalArgumentException("La combinaison rentrée est trop courte !");
        }

        Combinaison result = new Combinaison(tailleMax);
        for (int i = 0; i < response.length(); i++){
            int indexCouleur = Character.getNumericValue(response.charAt(i))-1;
            if (indexCouleur + 1 > couleursAutorisees.size()){
                throw new IllegalArgumentException("Le " + (i+1) +"-ème chiffre de la combinaison est supérieur à " + couleursAutorisees.size() + " !");
            }
            result.addElement(new Pion(couleursAutorisees.get(indexCouleur)));
        }
        return result;
    }
    @Override
    public Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees, String messageErr) throws IllegalArgumentException, SaveSignal {

        System.out.println("Veuillez rentrer la combinaison de chiffres correspondant aux couleurs que vous désirez pour votre essai.");
        for (int i = 0; i < couleursAutorisees.size(); i++){
            System.out.print((i+1) + " pour " + couleursAutorisees.get(i) + couleursAutorisees.get(i).getNom() + Couleur.RESET + "; ");
        }
        Combinaison exemple = new Combinaison(4);
        exemple.addElement(new Pion(couleursAutorisees.get(0)));
        exemple.addElement(new Pion(couleursAutorisees.get(1)));
        exemple.addElement(new Pion(couleursAutorisees.get(2)));
        exemple.addElement(new Pion(couleursAutorisees.get(3)));
        System.out.println("\nExemple : 1234 correspond à la combinaison " + exemple);
        System.out.println("(Sauvegardez votre partie à tout moment en rentrant 'S')");
        if (messageErr != null) {
            afficherErreur(messageErr);
        }

        String res = getInput();
        clear();

        if (res.equals("s") || res.equals("S")){
            throw new SaveSignal();
        }

        return parse(res, tailleMax, couleursAutorisees);
    }

    @Override
    public String choixMenu(boolean sigerr) {
        clear();
        System.out.println("1. Mode Solo");
        System.out.println("2. Mode Multi");
        System.out.println("q. Quitter");
        if (sigerr) {
            afficherErreur("Choix invalide, veuillez rentrer un caractère valide.");
        }
        return getInput();
    }
}
