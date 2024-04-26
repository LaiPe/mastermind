package gui;

import entities.Couleur;
import entities.Pion;
import jeu.Plateau;
import listes.Combinaison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalGUI implements GUI {
    private boolean affichageTexte;

    public TerminalGUI(boolean affichageTexte){
        this.affichageTexte = affichageTexte;
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

    private Combinaison parse(String response, int tailleMax) throws IllegalArgumentException{
        if (response.length() > tailleMax){
            throw new IllegalArgumentException("La combinaison rentrée est trop longue !");
        }

        Combinaison result = new Combinaison(tailleMax);
        for (int i = 0; i < response.length(); i++){
            int indexCouleur = Character.getNumericValue(response.charAt(i));
            if (indexCouleur > 8){
                throw new IllegalArgumentException("Le " + (i+1) +"-ème chiffre de la combinaison est supérieur à 8 !");
            }
            result.addElement(new Pion(Couleur.getByIndex(indexCouleur)));
        }
        return result;
    }
    @Override
    public Combinaison choixCombinaison(int tailleMax) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Veuillez rentrer la combinaison de chiffres correspondant aux couleurs que vous désirez pour votre essai.");
            for (int i = 1; i < 9; i++){
                Couleur c = Couleur.getByIndex(i);
                System.out.print(i + " pour " + c + c.getNom() + Couleur.RESET + "; ");
            }
            Combinaison exemple = new Combinaison(4);
            exemple.addElement(new Pion(Couleur.getByIndex(4)));
            exemple.addElement(new Pion(Couleur.getByIndex(5)));
            exemple.addElement(new Pion(Couleur.getByIndex(1)));
            exemple.addElement(new Pion(Couleur.getByIndex(2)));
            System.out.println("\nExemple : 4512 correspond à la combinaison " + exemple);


            String res = reader.readLine();

            return parse(res, tailleMax);

        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
