package jeu;

import entities.CombinaisonPions;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final CombinaisonSecrete combinaisonSecrete;
    private final List<CombinaisonPions> essaisCombinaison;

    public Plateau(CombinaisonSecrete combinaisonSecrete, int nbEssaisMax, int tailleMaxCombinaison){
        this.combinaisonSecrete = combinaisonSecrete;

        essaisCombinaison = new ArrayList<>();
        for (int i = 0; i < nbEssaisMax; i++){
            essaisCombinaison.add(new CombinaisonPions(tailleMaxCombinaison));
        }
    }

    public CombinaisonSecrete getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public void setEssai(int index, CombinaisonPions combinaison) {
        essaisCombinaison.set(index, combinaison);
    }
    public CombinaisonPions getEssai(int index) {
        return essaisCombinaison.get(index);
    }



}
