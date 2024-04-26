package jeu;

import listes.Combinaison;
import listes.CombinaisonSecrete;
import listes.TentativeResult;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final CombinaisonSecrete combinaisonSecrete;
    private final List<Combinaison> essaisCombinaison;
    private final List<TentativeResult> essaisResult;
    private final int nbEssaisMax;

    public Plateau(CombinaisonSecrete combinaisonSecrete, int nbEssaisMax, int tailleMaxCombinaison){
        this.combinaisonSecrete = combinaisonSecrete;

        this.nbEssaisMax = nbEssaisMax;
        essaisCombinaison = new ArrayList<>();
        essaisResult = new ArrayList<>();
    }

    public CombinaisonSecrete getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public void addEssai(Combinaison essai) {
        essaisCombinaison.add(essai);
        essaisResult.add(combinaisonSecrete.compare(essai));
    }
    public Combinaison getCombinaisonEssai(int index) {
        return essaisCombinaison.get(index);
    }
    public TentativeResult getResultEssai(int index) {
        return essaisResult.get(index);
    }

    public int getNbEssaisMax() {
        return nbEssaisMax;
    }
}
