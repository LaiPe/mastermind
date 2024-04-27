package gui;

import entities.Couleur;
import jeu.Plateau;
import listes.Combinaison;
import listes.RulesList;
import rules.Rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public interface GUI {
    void afficherPlateau(Plateau plateau);

    Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees);

    static RulesList choixRules(){
        RulesList li = new RulesList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < li.getTaille(); i++){
            Rule rule = li.getElement(i);
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

        try {
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return li;
    }
}
