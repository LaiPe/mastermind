package gui;

import entities.Couleur;
import jeu.Plateau;
import listes.Combinaison;
import listes.RulesList;
import rules.Rule;
import rules.map.MapRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GUI {
    void afficherPlateau(Plateau plateau);

    Combinaison choixCombinaison(int tailleMax, List<Couleur> couleursAutorisees);

    static MapRule choixRules(MapRule rules){
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
}
