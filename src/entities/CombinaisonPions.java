package entities;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonPions {
    private List<Pion> combinaisonPions;
    private int tailleMax;

    public CombinaisonPions(int tailleMax){
        combinaisonPions = new ArrayList<>(tailleMax);
        this.tailleMax = tailleMax;
    }
    public Pion getPion(int index){
        if (index < combinaisonPions.size()) {
            Pion result = combinaisonPions.get(index);
            return result;
        }
        else {
            System.err.println("Index invalide !");
            return null;
        }
    }
    public void addPion(Pion p){
        if (combinaisonPions.size() < tailleMax) {
            combinaisonPions.add(p);
        } else {
            System.err.println("Combinaison pleine !");
        }
    }
    public int getTaille(){
        return combinaisonPions.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < combinaisonPions.size() ; i++){
            result.append(combinaisonPions.get(i).toString());
        }

        for (int i = combinaisonPions.size() ; i < tailleMax ; i++){
            result.append(".");
        }

        return result.toString();
    }
}