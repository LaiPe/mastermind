package jeu;

import entities.Result;
import listes.Combinaison;
import listes.CombinaisonSecrete;
import listes.TentativeResult;

import java.sql.ResultSet;
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
        for (int i = 0; i < nbEssaisMax; i++){
            essaisCombinaison.add(new Combinaison(tailleMaxCombinaison));
        }
        essaisResult = new ArrayList<>();
        for (int i = 0; i < nbEssaisMax; i++){
            essaisResult.add(new TentativeResult(tailleMaxCombinaison));
        }
    }

    public CombinaisonSecrete getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public void setEssai(int index, Combinaison essai) {
        essaisCombinaison.set(index, essai);
        essaisResult.add(index, combinaisonSecrete.compare(essai));
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
