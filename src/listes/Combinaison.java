package listes;

import entities.Pion;

import java.util.ArrayList;
import java.util.List;

public class Combinaison {
    private final List<Pion> combinaisonPions;
    private final int tailleMax;

    public Combinaison(int tailleMax){
        combinaisonPions = new ArrayList<>(tailleMax);
        this.tailleMax = tailleMax;
    }

    public Pion getPion(int index) throws IndexOutOfBoundsException{
        if (index < combinaisonPions.size()) {
            return combinaisonPions.get(index);
        }
        else {
            System.err.println("Index invalide !");
            throw new IndexOutOfBoundsException();
        }
    }

    public void addPion(Pion p) throws IndexOutOfBoundsException{
        if (combinaisonPions.size() < tailleMax) {
            combinaisonPions.add(p);
        } else {
            System.err.println("Combinaison pleine !");
            throw new IndexOutOfBoundsException();
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
