package gui;

import entities.Couleur;
import entities.Pion;
import jeu.Plateau;
import listes.Combinaison;
import rules.Rule;
import rules.map.MapRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TerminalGUI implements GUI {
    private boolean affichageTexte;

    public TerminalGUI(){}

    @Override
    public void setAffichageTexte(boolean affichageTexte) {
        this.affichageTexte = affichageTexte;
    }

    @Override
    public MapRule choixRules(MapRule rules){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for(Rule rule : rules.allRules()){
            boolean validRes = false;
            while (!validRes) {
                System.out.print(rule.getDemande() + " : ");
                try {
                    String res = reader.readLine();
                    rule.setValue(res);
                    validRes = true;
                } catch (IOException e){
                    e.printStackTrace();
                } catch (IllegalArgumentException e){
                    System.out.println(Couleur.ROUGE + e.getMessage() + Couleur.RESET);
                }
            }
        }
        return rules;
    }

    @Override
    public void afficherPlateau(Plateau plateau){
        System.out.println("\033[H\033[2J");

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

        System.out.println();
    }

    @Override
    public void afficherInfosJoueur(int i) {
        System.out.println(Couleur.getByIndex(1 + i%8) + "============== Joueur " + i + " ==============" + Couleur.RESET);
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
    public Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
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


            String res = reader.readLine();

            return parse(res, tailleMax, couleursAutorisees);

        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }

        return null;
    }
}
