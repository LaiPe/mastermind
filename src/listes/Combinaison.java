package listes;

import entities.Pion;

public class Combinaison extends EntityList<Pion> {
    public Combinaison(int tailleMax) {
        super(tailleMax);
    }

    public String toIndexString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < getTaille() ; i++){
            result.append(getElement(i).getCouleurPion().getIndex());
        }

        for (int i = getTaille() ; i < getTailleMax() ; i++){
            result.append(".");
        }

        return result.toString();
    }
}
