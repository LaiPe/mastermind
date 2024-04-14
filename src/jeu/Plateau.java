package jeu;

import listes.Combinaison;
import listes.CombinaisonSecrete;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final CombinaisonSecrete combinaisonSecrete;
    private final List<Combinaison> essaisCombinaison;

    public Plateau(CombinaisonSecrete combinaisonSecrete, int nbEssaisMax, int tailleMaxCombinaison){
        this.combinaisonSecrete = combinaisonSecrete;

        essaisCombinaison = new ArrayList<>();
        for (int i = 0; i < nbEssaisMax; i++){
            essaisCombinaison.add(new Combinaison(tailleMaxCombinaison));
        }
    }

    public CombinaisonSecrete getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public void setEssai(int index, Combinaison combinaison) {
        essaisCombinaison.set(index, combinaison);
    }
    public Combinaison getEssai(int index) {
        return essaisCombinaison.get(index);
    }



}
